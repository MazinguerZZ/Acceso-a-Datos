package JPA_Ejer2_3_4;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScottCRUDCompleto {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadScott2");
		EntityManager em = emf.createEntityManager();
		System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");

		Scanner scanner = new Scanner(System.in);

		try {
			while (true) {
				System.out.println("\n=== MENÚ CRUD SCOTT2 (HIBERNATE) ===");
				System.out.println("1. CREATE - Crear datos iniciales");
				System.out.println("2. READ - Mostrar todos los datos");
				System.out.println("3. UPDATE - Modificar empleado");
				System.out.println("4. DELETE - Eliminar proyecto");
				System.out.println("5. CONSULTAS JPQL Avanzadas");
				System.out.println("6. Salir");
				System.out.print("Selecciona opción: ");

				int opcion = scanner.nextInt();
				scanner.nextLine();

				switch (opcion) {
				case 1:
					createDatos(em);
					break;
				case 2:
					readDatos(em);
					break;
				case 3:
					updateDatos(em, scanner);
					break;
				case 4:
					deleteDatos(em, scanner);
					break;
				case 5:
					ejecutarConsultasAvanzadas(em);
					break;
				case 6:
					System.out.println("Saliendo...");
					return;
				default:
					System.out.println("Opción no válida");
				}
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();
			em.close();
			emf.close();
		}
	}

	private static void createDatos(EntityManager em) {
		em.getTransaction().begin();

		try {
			// Crear empleados
			Scott2 emp1 = new Scott2(7369L, "SMITH", "CLERK", 7902, 800, null, 20);
			Scott2 emp2 = new Scott2(7499L, "ALLEN", "SALESMAN", 7698, 1600, 300, 30);
			Scott2 emp3 = new Scott2(7521L, "WARD", "SALESMAN", 7698, 1250, 500, 30);
			Scott2 emp4 = new Scott2(7566L, "JONES", "MANAGER", 7839, 2975, null, 20);

			// Crear habilidades
			Habilidad h1 = new Habilidad("Java", "Avanzado");
			Habilidad h2 = new Habilidad("SQL", "Intermedio");
			Habilidad h3 = new Habilidad("HTML", "Básico");
			Habilidad h4 = new Habilidad("Spring", "Avanzado");
			Habilidad h5 = new Habilidad("JavaScript", "Intermedio");

			// Crear proyectos
			Proyecto p1 = new Proyecto("Sistema Contable", "Desarrollo del sistema contable principal");
			Proyecto p2 = new Proyecto("App Web", "Aplicación web para ventas online");
			Proyecto p3 = new Proyecto("Base de Datos", "Migración de base de datos a nueva versión");
			Proyecto p4 = new Proyecto("API REST", "Desarrollo de API REST para clientes");

			// Establecer relaciones
			emp1.addProyecto(p1);
			emp1.addProyecto(p4);
			emp2.addProyecto(p2);
			emp3.addProyecto(p3);
			emp4.addProyecto(p1);
			emp4.addProyecto(p2);

			emp1.addHabilidad(h1);
			emp1.addHabilidad(h2);
			emp1.addHabilidad(h4);
			emp2.addHabilidad(h2);
			emp2.addHabilidad(h3);
			emp2.addHabilidad(h5);
			emp3.addHabilidad(h3);
			emp3.addHabilidad(h5);
			emp4.addHabilidad(h1);
			emp4.addHabilidad(h4);

			// Persistir
			em.persist(emp1);
			em.persist(emp2);
			em.persist(emp3);
			em.persist(emp4);

			em.getTransaction().commit();
			System.out.println("Datos creados exitosamente");
			System.out.println("- 4 empleados creados");
			System.out.println("- 5 habilidades creadas");
			System.out.println("- 4 proyectos creados");

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Error al crear datos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void readDatos(EntityManager em) {
		try {
			// Leer empleados con proyectos usando JOIN FETCH
			List<Scott2> empleados = em.createQuery(
					"SELECT DISTINCT e FROM Scott2 e " + "LEFT JOIN FETCH e.proyectos " + "ORDER BY e.empno",
					Scott2.class).getResultList();

			System.out.println("\n=== EMPLEADOS Y SUS PROYECTOS ===");
			if (empleados.isEmpty()) {
				System.out.println("No hay empleados registrados");
				return;
			}

			for (Scott2 emp : empleados) {
				System.out.println("\n" + emp.getEname() + " (" + emp.getJob() + ")");
				System.out.println(
						"  ID: " + emp.getEmpno() + ", Salario: " + emp.getSal() + ", Dept: " + emp.getDeptno());

				if (!emp.getProyectos().isEmpty()) {
					System.out.println("  Proyectos asignados:");
					for (Proyecto p : emp.getProyectos()) {
						System.out.println("    • " + p.getNombre() + " - " + p.getDescripcion());
					}
				} else {
					System.out.println("  Sin proyectos asignados");
				}

				// Cargar habilidades por separado
				List<Habilidad> habilidades = em
						.createQuery("SELECT DISTINCT h FROM Habilidad h " + "JOIN h.empleados e "
								+ "WHERE e.empno = :empno " + "ORDER BY h.nombre", Habilidad.class)
						.setParameter("empno", emp.getEmpno()).getResultList();

				if (!habilidades.isEmpty()) {
					System.out.println("  Habilidades:");
					for (Habilidad h : habilidades) {
						System.out.println("    • " + h.getNombre() + " (" + h.getNivel() + ")");
					}
				} else {
					System.out.println("  Sin habilidades registradas");
				}
			}

		} catch (Exception e) {
			System.err.println("Error al leer datos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void updateDatos(EntityManager em, Scanner scanner) {
		System.out.print("ID del empleado a modificar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		em.getTransaction().begin();

		try {
			Scott2 emp = em.find(Scott2.class, id);
			if (emp != null) {
				System.out.println("Empleado encontrado: " + emp.getEname());
				System.out.println("Datos actuales:");
				System.out.println("  Salario: " + emp.getSal());
				System.out.println("  Departamento: " + emp.getDeptno());
				System.out.println("  Puesto: " + emp.getJob());

				System.out.print("Nuevo salario (actual " + emp.getSal() + "): ");
				String nuevoSalarioStr = scanner.nextLine();
				if (!nuevoSalarioStr.trim().isEmpty()) {
					int nuevoSalario = Integer.parseInt(nuevoSalarioStr);
					int salarioAnterior = emp.getSal();
					emp.setSal(nuevoSalario);
					System.out.println("Salario actualizado de " + salarioAnterior + " a " + nuevoSalario);
				}

				System.out.print("Nuevo departamento (actual " + emp.getDeptno() + "): ");
				String nuevoDeptoStr = scanner.nextLine();
				if (!nuevoDeptoStr.trim().isEmpty()) {
					int nuevoDepto = Integer.parseInt(nuevoDeptoStr);
					emp.setDeptno(nuevoDepto);
					System.out.println("Departamento actualizado a " + nuevoDepto);
				}

				System.out.print("Nuevo puesto (actual '" + emp.getJob() + "'): ");
				String nuevoPuesto = scanner.nextLine();
				if (!nuevoPuesto.trim().isEmpty()) {
					emp.setJob(nuevoPuesto);
					System.out.println("Puesto actualizado a '" + nuevoPuesto + "'");
				}

				em.getTransaction().commit();
				System.out.println("Empleado actualizado correctamente");
			} else {
				System.out.println("Empleado no encontrado con ID: " + id);
				em.getTransaction().rollback();
			}

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Error al actualizar: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void deleteDatos(EntityManager em, Scanner scanner) {
		System.out.print("Nombre exacto del proyecto a eliminar: ");
		String nombreProyecto = scanner.nextLine();

		em.getTransaction().begin();

		try {
			TypedQuery<Proyecto> query = em.createQuery("SELECT p FROM Proyecto p WHERE p.nombre = :nombre",
					Proyecto.class);
			query.setParameter("nombre", nombreProyecto);

			List<Proyecto> proyectos = query.getResultList();

			if (!proyectos.isEmpty()) {
				Proyecto proyecto = proyectos.get(0);
				System.out.println("Proyecto encontrado: " + proyecto.getNombre());
				System.out.println("Descripción: " + proyecto.getDescripcion());

				// Desasociar el proyecto del empleado
				if (proyecto.getEmpleado() != null) {
					Scott2 empleado = proyecto.getEmpleado();
					System.out.println("Asignado a: " + empleado.getEname());
					empleado.getProyectos().remove(proyecto);
					proyecto.setEmpleado(null);
				}

				em.remove(proyecto);
				em.getTransaction().commit();
				System.out.println("Proyecto '" + proyecto.getNombre() + "' eliminado correctamente");
			} else {
				System.out.println("Proyecto no encontrado con nombre: " + nombreProyecto);
				em.getTransaction().rollback();
			}

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Error al eliminar: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void ejecutarConsultasAvanzadas(EntityManager em) {
		System.out.println("\n=== CONSULTAS JPQL AVANZADAS ===");

		try {
			// 1. JOIN con múltiples tablas
			System.out.println("\n1. JOIN entre empleados, proyectos y habilidades:");
			List<Object[]> resultados1 = em
					.createQuery("SELECT e.ename, p.nombre, h.nombre " + "FROM Scott2 e " + "LEFT JOIN e.proyectos p "
							+ "LEFT JOIN e.habilidades h " + "ORDER BY e.ename, p.nombre", Object[].class)
					.getResultList();

			for (Object[] row : resultados1) {
				System.out.println("Empleado: " + row[0] + ", Proyecto: " + (row[1] != null ? row[1] : "Ninguno")
						+ ", Habilidad: " + (row[2] != null ? row[2] : "Ninguna"));
			}

			// 2. GROUP BY y HAVING
			System.out.println("\n2. Número de proyectos por empleado:");
			List<Object[]> resultados2 = em
					.createQuery("SELECT e.ename, COUNT(p) " + "FROM Scott2 e LEFT JOIN e.proyectos p "
							+ "GROUP BY e.empno, e.ename " + "ORDER BY COUNT(p) DESC", Object[].class)
					.getResultList();

			for (Object[] row : resultados2) {
				System.out.println("Empleado: " + row[0] + ", Proyectos: " + row[1]);
			}

			// 3. Subconsulta
			System.out.println("\n3. Empleados con salario superior al promedio:");
			List<Scott2> resultados3 = em.createQuery("SELECT e FROM Scott2 e "
					+ "WHERE e.sal > (SELECT AVG(e2.sal) FROM Scott2 e2) " + "ORDER BY e.sal DESC", Scott2.class)
					.getResultList();

			for (Scott2 emp : resultados3) {
				System.out.println(emp.getEname() + ": " + emp.getSal());
			}

			// 4. LIKE con comodines
			System.out.println("\n4. Empleados cuyo nombre empieza con 'S' o 'A':");
			List<Scott2> resultados4 = em.createQuery(
					"SELECT e FROM Scott2 e " + "WHERE e.ename LIKE 'S%' OR e.ename LIKE 'A%' " + "ORDER BY e.ename",
					Scott2.class).getResultList();

			for (Scott2 emp : resultados4) {
				System.out.println(emp.getEname() + " - " + emp.getJob());
			}

			// 5. CASE WHEN
			System.out.println("\n5. Clasificación de salarios:");
			List<Object[]> resultados5 = em
					.createQuery("SELECT e.ename, e.sal, " + "CASE " + "  WHEN e.sal < 1000 THEN 'Bajo' "
							+ "  WHEN e.sal BETWEEN 1000 AND 2000 THEN 'Medio' " + "  ELSE 'Alto' "
							+ "END as categoria " + "FROM Scott2 e " + "ORDER BY e.sal DESC", Object[].class)
					.getResultList();

			for (Object[] row : resultados5) {
				System.out.println(row[0] + ": $" + row[1] + " (" + row[2] + ")");
			}

			// 6. COALESCE para valores nulos
			System.out.println("\n6. Empleados con comisión (usando COALESCE):");
			List<Object[]> resultados6 = em.createQuery("SELECT e.ename, COALESCE(e.comm, 0) as comision "
					+ "FROM Scott2 e " + "ORDER BY COALESCE(e.comm, 0) DESC", Object[].class).getResultList();

			for (Object[] row : resultados6) {
				System.out.println(row[0] + ": Comisión $" + row[1]);
			}

			// 7. INNER JOIN con filtro
			System.out.println("\n7. Proyectos con empleados asignados:");
			List<Object[]> resultados7 = em.createQuery("SELECT p.nombre, e.ename, e.job " + "FROM Proyecto p "
					+ "INNER JOIN p.empleado e " + "ORDER BY p.nombre", Object[].class).getResultList();

			for (Object[] row : resultados7) {
				Arrays.sort(row);
				System.out.println("Proyecto: " + row[0] + " → Asignado a: " + row[1] + " (" + row[2] + ")");
			}

		} catch (Exception e) {
			System.err.println("Error en consultas: " + e.getMessage());
			e.printStackTrace();
		}
	}
}