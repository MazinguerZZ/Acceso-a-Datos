import jakarta.persistence.*;
import java.util.List;

public class ConsultasJPQL {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		System.out.println("=== CONSULTAS JPQL COMPLETAS CON HIBERNATE 6 ===");
		System.out.println("Demostrando todas las consultas requeridas\n");
		System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");

		try {
			// 1. Inicializar JPA
			emf = Persistence.createEntityManagerFactory("UnidadScott");
			em = emf.createEntityManager();

			// Ejecutar todas las consultas
			ejecutarTodasLasConsultas();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen())
				em.close();
			if (emf != null && emf.isOpen())
				emf.close();
		}
	}

	private static void ejecutarTodasLasConsultas() {
		System.out.println("=".repeat(70));
		System.out.println("1. LISTADO CON CAMPOS ESPECÍFICOS");
		System.out.println("=".repeat(70));
		consulta1_camposEspecificos();

		System.out.println("\n" + "=".repeat(70));
		System.out.println("2. LISTADO FILTRADO (WHERE)");
		System.out.println("=".repeat(70));
		consulta2_filtradoWhere();

		System.out.println("\n" + "=".repeat(70));
		System.out.println("3. LISTADO ORDENADO");
		System.out.println("=".repeat(70));
		consulta3_ordenado();

		System.out.println("\n" + "=".repeat(70));
		System.out.println("4. BÚSQUEDA EXACTA Y APROXIMADA");
		System.out.println("=".repeat(70));
		consulta4_busquedaExactaAproximada();

		System.out.println("\n" + "=".repeat(70));
		System.out.println("5. RANGO NUMÉRICO (BETWEEN)");
		System.out.println("=".repeat(70));
		consulta5_rangoNumerico();

		System.out.println("\n" + "=".repeat(70));
		System.out.println("6. FUNCIONES DE AGREGACIÓN");
		System.out.println("=".repeat(70));
		consulta6_funcionesAgregacion();

		System.out.println("\n" + "=".repeat(70));
		System.out.println("7. JOIN ENTRE TABLAS");
		System.out.println("=".repeat(70));
		consulta7_joinTablas();
	}

	// ============================================
	// 1. LISTADO CON CAMPOS ESPECÍFICOS
	// ============================================
	private static void consulta1_camposEspecificos() {
		System.out.println("Seleccionando solo: EMPNO, ENAME, JOB, SAL");
		System.out.println("-".repeat(50));

		List<Object[]> resultados = em
				.createQuery("SELECT e.empno, e.ename, e.job, e.sal FROM Scott e ORDER BY e.empno", Object[].class)
				.getResultList();

		if (resultados.isEmpty()) {
			System.out.println("No hay datos disponibles");
			return;
		}

		// Encabezado
		System.out.printf("%-8s %-12s %-15s %-10s%n", "EMPNO", "ENAME", "JOB", "SAL");
		System.out.println("-".repeat(45));

		// Datos
		for (Object[] fila : resultados) {
			Long empno = (Long) fila[0];
			String ename = (String) fila[1];
			String job = (String) fila[2];
			Integer sal = (Integer) fila[3];

			System.out.printf("%-8d %-12s %-15s $%-9d%n", empno, ename, job, sal);
		}

		System.out.println("\n✓ Total registros: " + resultados.size());
	}

	// ============================================
	// 2. LISTADO FILTRADO (WHERE)
	// ============================================
	private static void consulta2_filtradoWhere() {
		System.out.println("Empleados del departamento 20 con salario > 1100");
		System.out.println("-".repeat(50));

		List<Scott> resultados = em
				.createQuery("SELECT e FROM Scott e WHERE e.deptno = :depto AND e.sal > :salMin ORDER BY e.sal DESC",
						Scott.class)
				.setParameter("depto", 20).setParameter("salMin", 1100).getResultList();

		if (resultados.isEmpty()) {
			System.out.println("No hay empleados que cumplan los criterios");
			return;
		}

		for (Scott emp : resultados) {
			System.out.printf(" %-10s (ID: %d) - %-10s - Salario: $%d - Dept: %d%n", emp.getEname(), emp.getEmpno(),
					emp.getJob(), emp.getSal(), emp.getDeptno());
		}

		System.out.println("\n Filtro aplicado: DEPTNO = 20 AND SAL > 1100");
		System.out.println(" Resultados: " + resultados.size() + " empleados");
	}

	// ============================================
	// 3. LISTADO ORDENADO
	// ============================================
	private static void consulta3_ordenado() {
		System.out.println("Empleados ordenados por:");
		System.out.println("1. Departamento (ASC)");
		System.out.println("2. Salario (DESC)");
		System.out.println("3. Nombre (ASC)");
		System.out.println("-".repeat(50));

		List<Scott> resultados = em
				.createQuery("SELECT e FROM Scott e ORDER BY e.deptno ASC, e.sal DESC, e.ename ASC", Scott.class)
				.getResultList();

		if (resultados.isEmpty()) {
			System.out.println("No hay empleados");
			return;
		}

		Integer deptActual = null;
		for (Scott emp : resultados) {
			// Mostrar separador por departamento
			if (deptActual == null || !deptActual.equals(emp.getDeptno())) {
				deptActual = emp.getDeptno();
				System.out.println("\nDEPARTAMENTO " + deptActual + ":");
				System.out.println("-".repeat(40));
			}

			System.out.printf("  %-10s - %-10s - Salario: $%-6d%n", emp.getEname(), emp.getJob(), emp.getSal());
		}

		System.out.println("\n Orden: DEPTNO ↑, SAL ↓, ENAME ↑");
	}

	// ============================================
	// 4. BÚSQUEDA EXACTA Y APROXIMADA
	// ============================================
	private static void consulta4_busquedaExactaAproximada() {
		// Búsqueda EXACTA
		System.out.println("A) BÚSQUEDA EXACTA:");
		System.out.println("   Empleado con EMPNO = 7002");
		System.out.println("   ".repeat(20));

		Scott empleadoExacto = em.createQuery("SELECT e FROM Scott e WHERE e.empno = :id", Scott.class)
				.setParameter("id", 7002L).getResultStream().findFirst().orElse(null);

		if (empleadoExacto != null) {
			System.out.println("   ENCONTRADO: " + empleadoExacto.getEname() + " - " + empleadoExacto.getJob() + " - $"
					+ empleadoExacto.getSal());
		} else {
			System.out.println("   NO ENCONTRADO");
		}

		// Búsqueda APROXIMADA
		System.out.println("\nB) BÚSQUEDA APROXIMADA (LIKE):");
		System.out.println("   Empleados cuyo nombre contiene 'dr' (case-insensitive)");
		System.out.println("   ".repeat(20));

		List<Scott> resultadosAprox = em
				.createQuery("SELECT e FROM Scott e WHERE LOWER(e.ename) LIKE LOWER(:patron) ORDER BY e.ename",
						Scott.class)
				.setParameter("patron", "%dr%").getResultList();

		if (resultadosAprox.isEmpty()) {
			System.out.println("   No se encontraron coincidencias");
		} else {
			resultadosAprox.forEach(emp -> System.out.println("    " + emp.getEname() + " - ID: " + emp.getEmpno()));
		}

		// Búsqueda con IN (valores exactos múltiples)
		System.out.println("\nC) BÚSQUEDA CON MÚLTIPLES VALORES (IN):");
		System.out.println("   Empleados con EMPNO 7000, 7002, 7005");
		System.out.println("   ".repeat(20));

		List<Scott> resultadosIn = em
				.createQuery("SELECT e FROM Scott e WHERE e.empno IN (:ids) ORDER BY e.empno", Scott.class)
				.setParameter("ids", List.of(7000L, 7002L, 7005L)).getResultList();

		System.out.println("   Encontrados: " + resultadosIn.size() + " de 3");
		resultadosIn.forEach(emp -> System.out.println("   ID " + emp.getEmpno() + ": " + emp.getEname()));
	}

	// ============================================
	// 5. RANGO NUMÉRICO (BETWEEN)
	// ============================================
	private static void consulta5_rangoNumerico() {
		System.out.println("Empleados con salario entre 1000 y 1200");
		System.out.println("Y comisión entre 500 y 700");
		System.out.println("-".repeat(50));

		List<Scott> resultados = em
				.createQuery(
						"SELECT e FROM Scott e " + "WHERE e.sal BETWEEN :salMin AND :salMax "
								+ "AND e.comm BETWEEN :commMin AND :commMax " + "ORDER BY e.sal ASC, e.comm ASC",
						Scott.class)
				.setParameter("salMin", 1000).setParameter("salMax", 1200).setParameter("commMin", 500)
				.setParameter("commMax", 700).getResultList();

		if (resultados.isEmpty()) {
			System.out.println("No hay empleados en esos rangos");
		} else {
			System.out.println("EMPNO   NOMBRE      SALARIO   COMISIÓN");
			System.out.println("-".repeat(40));

			for (Scott emp : resultados) {
				System.out.printf("%-7d %-10s $%-8d $%-8d%n", emp.getEmpno(), emp.getEname(), emp.getSal(),
						emp.getComm());
			}
		}

		System.out.println("\n Rangos: SAL [1000-1200], COMM [500-700]");
		System.out.println("Resultados: " + resultados.size() + " empleados");
	}

	// ============================================
	// 6. FUNCIONES DE AGREGACIÓN
	// ============================================
	private static void consulta6_funcionesAgregacion() {
		System.out.println("ESTADÍSTICAS POR DEPARTAMENTO");
		System.out.println("-".repeat(50));

		// Estadísticas generales
		System.out.println("\nA) ESTADÍSTICAS GENERALES:");

		Long totalEmpleados = em.createQuery("SELECT COUNT(e) FROM Scott e", Long.class).getSingleResult();

		Double salarioPromedio = em.createQuery("SELECT AVG(e.sal) FROM Scott e", Double.class).getSingleResult();

		Number sumaSalarios = em.createQuery("SELECT SUM(e.sal) FROM Scott e", Number.class).getSingleResult();

		Integer salarioMaximo = em.createQuery("SELECT MAX(e.sal) FROM Scott e", Integer.class).getSingleResult();

		Integer salarioMinimo = em.createQuery("SELECT MIN(e.sal) FROM Scott e", Integer.class).getSingleResult();

		System.out.println("   • Total empleados: " + totalEmpleados);
		System.out.println("   • Salario promedio: $" + String.format("%.2f", salarioPromedio));
		System.out.println("   • Suma salarios: $" + sumaSalarios);
		System.out.println("   • Salario máximo: $" + salarioMaximo);
		System.out.println("   • Salario mínimo: $" + salarioMinimo);

		// Estadísticas por departamento (GROUP BY)
		System.out.println("\nB) ESTADÍSTICAS POR DEPARTAMENTO (GROUP BY):");

		List<Object[]> resultadosDept = em.createQuery(
				"SELECT e.deptno, " + "       COUNT(e) as cantidad, " + "       AVG(e.sal) as promedio, "
						+ "       SUM(e.sal) as total, " + "       MAX(e.sal) as maximo, "
						+ "       MIN(e.sal) as minimo " + "FROM Scott e " + "GROUP BY e.deptno " + "ORDER BY e.deptno",
				Object[].class).getResultList();

		if (resultadosDept.isEmpty()) {
			System.out.println("   No hay datos por departamento");
		} else {
			System.out.println("   DEPTO  EMPLEADOS  PROMEDIO    TOTAL      MÁX    MÍN");
			System.out.println("   ".repeat(20));

			for (Object[] fila : resultadosDept) {
				Integer deptno = (Integer) fila[0];
				Long cantidad = (Long) fila[1];
				Double promedio = (Double) fila[2];
				Number total = (Number) fila[3];
				Integer maximo = (Integer) fila[4];
				Integer minimo = (Integer) fila[5];

				System.out.printf("   %-6d %-10d $%-9.2f $%-9d $%-6d $%-6d%n", deptno, cantidad, promedio,
						total.intValue(), maximo, minimo);
			}
		}

		// Estadísticas con HAVING
		System.out.println("\nC) DEPARTAMENTOS CON MÁS DE 1 EMPLEADO (HAVING):");

		List<Object[]> resultadosHaving = em.createQuery("SELECT e.deptno, COUNT(e) as cantidad " + "FROM Scott e "
				+ "GROUP BY e.deptno " + "HAVING COUNT(e) > 1 " + "ORDER BY COUNT(e) DESC", Object[].class)
				.getResultList();

		if (resultadosHaving.isEmpty()) {
			System.out.println("   No hay departamentos con más de 1 empleado");
		} else {
			resultadosHaving.forEach(fila -> System.out.printf("   Depto %d: %d empleados%n", fila[0], fila[1]));
		}
	}

	// ============================================
	// 7. JOIN ENTRE TABLAS
	// ============================================
	private static void consulta7_joinTablas() {
		System.out.println("Para JOIN necesitamos usar la unidad de persistencia UnidadScott2");
		System.out.println("que tiene relaciones entre Scott2, Proyecto y Habilidad");
		System.out.println("-".repeat(70));

		// Cerrar el EntityManager actual
		if (em != null && em.isOpen()) {
			em.close();
		}
		if (emf != null && emf.isOpen()) {
			emf.close();
		}

		try {
			// Crear nuevo EntityManager para UnidadScott2
			EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("UnidadScott2");
			EntityManager em2 = emf2.createEntityManager();

			System.out.println("\nA) JOIN IMPLÍCITO (relaciones @ManyToOne/@OneToMany):");
			System.out.println("   Empleados con sus proyectos:");
			System.out.println("   ".repeat(20));

			List<Object[]> resultadosJoin = em2.createQuery("SELECT e.ename, e.job, p.nombre, p.descripcion "
					+ "FROM Scott2 e " + "JOIN e.proyectos p " + "ORDER BY e.ename, p.nombre", Object[].class)
					.getResultList();

			if (resultadosJoin.isEmpty()) {
				System.out.println("   Primero ejecuta ScottCRUDCompleto para crear datos de prueba");
				System.out.println("   o no hay relaciones creadas aún");
			} else {
				String empleadoActual = null;
				for (Object[] fila : resultadosJoin) {
					String ename = (String) fila[0];
					String job = (String) fila[1];
					String proyecto = (String) fila[2];
					String descripcion = (String) fila[3];

					if (empleadoActual == null || !empleadoActual.equals(ename)) {
						empleadoActual = ename;
						System.out.println("\n   " + ename + " (" + job + "):");
					}
					System.out.println("     • " + proyecto + ": " + descripcion);
				}
			}

			System.out.println("\nB) JOIN CON MULTIPLES TABLAS (ManyToMany):");
			System.out.println("   Empleados con sus habilidades:");
			System.out.println("   ".repeat(20));

			List<Object[]> resultadosJoin2 = em2.createQuery("SELECT e.ename, h.nombre, h.nivel " + "FROM Scott2 e "
					+ "JOIN e.habilidades h " + "ORDER BY e.ename, h.nombre", Object[].class).getResultList();

			if (resultadosJoin2.isEmpty()) {
				System.out.println("   No hay habilidades asignadas a empleados");
			} else {
				String empleadoActual = null;
				for (Object[] fila : resultadosJoin2) {
					String ename = (String) fila[0];
					String habilidad = (String) fila[1];
					String nivel = (String) fila[2];

					if (empleadoActual == null || !empleadoActual.equals(ename)) {
						empleadoActual = ename;
						System.out.println("\n   " + ename + ":");
					}
					System.out.println("     • " + habilidad + " (" + nivel + ")");
				}
			}

			System.out.println("\nC) LEFT JOIN (incluye empleados sin proyectos):");
			System.out.println("   Todos los empleados, tengan o no proyectos:");
			System.out.println("   ".repeat(20));

			List<Object[]> resultadosLeftJoin = em2.createQuery("SELECT e.ename, COUNT(p) as numProyectos "
					+ "FROM Scott2 e " + "LEFT JOIN e.proyectos p " + "GROUP BY e.empno, e.ename " + "ORDER BY e.ename",
					Object[].class).getResultList();

			if (resultadosLeftJoin.isEmpty()) {
				System.out.println("   No hay empleados en Scott2");
			} else {
				for (Object[] fila : resultadosLeftJoin) {
					String ename = (String) fila[0];
					Long numProyectos = (Long) fila[1];

					System.out.printf("   %-10s: %d proyecto(s)%n", ename, numProyectos);
				}
			}

			// Cerrar recursos
			em2.close();
			emf2.close();

		} catch (Exception e) {
			System.err.println("\n sError en JOIN: " + e.getMessage());
			System.out.println("   Asegúrate de que:");
			System.out.println("   1. La base de datos 'adat3' existe");
			System.out.println("   2. Has ejecutado ScottCRUDCompleto para crear datos");
		}
	}
}