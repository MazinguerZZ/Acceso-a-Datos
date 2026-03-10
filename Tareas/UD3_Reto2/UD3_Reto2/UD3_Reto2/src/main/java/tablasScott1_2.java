import java.util.List;
import java.util.Scanner;
import jakarta.persistence.*;

public class tablasScott1_2 {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadScott");
    private static EntityManager em = emf.createEntityManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {
            System.out.println("\n========== MENÚ SCOTT (HIBERNATE) ==========");
            System.out.println("1. Leer empleados");
            System.out.println("2. Actualizar salario");
            System.out.println("3. Eliminar todos los empleados");
            System.out.println("4. Crear un empleado nuevo");
            System.out.println("5. Ejecutar consultas JPQL");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    leerEmpleados();
                    break;
                case 2:
                    actualizarSalario();
                    break;
                case 3:
                    eliminarTodos();
                    break;
                case 4:
                    crearEmpleado();
                    break;
                case 5:
                    ejecutarConsultasJPQL();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 6);

        em.close();
        emf.close();
        sc.close();
    }

    private static void leerEmpleados() {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");

        List<Scott> lista = em.createQuery("SELECT a FROM Scott a", Scott.class).getResultList();

        if (lista.isEmpty()) {
            System.out.println("No hay empleados en la tabla.");
        } else {
            for (Scott a : lista) {
                imprimirEmpleado(a);
            }
        }
    }

    private static void actualizarSalario() {
        System.out.print("\nIntroduce EMPNO del empleado a actualizar: ");
        Long empno = sc.nextLong();
        sc.nextLine();

        System.out.print("Introduce el nuevo salario: ");
        Integer nuevoSal = sc.nextInt();
        sc.nextLine();

        em.getTransaction().begin();

        Scott emp = em.find(Scott.class, empno);

        if (emp != null) {
            emp.setSal(nuevoSal);
            em.merge(emp);
            em.getTransaction().commit();
            System.out.println("Salario actualizado correctamente.");
        } else {
            em.getTransaction().rollback();
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void eliminarTodos() {
        System.out.println("\nEliminando todos los registros...");

        em.getTransaction().begin();

        List<Scott> lista = em.createQuery("SELECT a FROM Scott a", Scott.class).getResultList();
        for (Scott s : lista) {
            em.remove(s);
        }

        em.getTransaction().commit();
        System.out.println("Todos los registros han sido eliminados.");
    }

    private static void crearEmpleado() {
        System.out.println("\n--- CREAR NUEVO EMPLEADO ---");

        System.out.print("EMPNO: ");
        Long empno = sc.nextLong();
        sc.nextLine();
        
        System.out.print("ENAME: ");
        String ename = sc.nextLine();
        
        System.out.print("JOB: ");
        String job = sc.nextLine();
        
        System.out.print("MGR: ");
        Integer mgr = sc.nextInt();
        sc.nextLine();
        
        System.out.print("SAL: ");
        Integer sal = sc.nextInt();
        sc.nextLine();
        
        System.out.print("COMM: ");
        Integer comm = sc.nextInt();
        sc.nextLine();
        
        System.out.print("DEPTNO: ");
        Integer deptno = sc.nextInt();
        sc.nextLine();

        Scott nuevo = new Scott(empno, ename, job, mgr, sal, comm, deptno);

        em.getTransaction().begin();
        em.persist(nuevo);
        em.getTransaction().commit();

        System.out.println("Empleado creado correctamente.");
    }

    private static void imprimirEmpleado(Scott a) {
        System.out.println(
                "EMPNO: " + a.getEmpno() +
                ", ENAME: " + a.getEname() +
                ", JOB: " + a.getJob() +
                ", MGR: " + a.getMgr() +
                ", SAL: " + a.getSal() +
                ", COMM: " + a.getComm() +
                ", DEPTNO: " + a.getDeptno()
        );
    }
    
    private static void ejecutarConsultasJPQL() {
        System.out.println("\n--- CONSULTAS JPQL ---");
        
        // Consulta 1: Seleccionar solo algunos campos
        System.out.println("\n1. Nombres y puestos de empleados:");
        List<Object[]> resultados1 = em.createQuery(
            "SELECT e.ename, e.job, e.sal FROM Scott e", Object[].class)
            .getResultList();
        for (Object[] row : resultados1) {
            System.out.println("Nombre: " + row[0] + ", Puesto: " + row[1] + ", Salario: " + row[2]);
        }
        
        // Consulta 2: WHERE con parámetro
        System.out.println("\n2. Empleados del departamento 20:");
        List<Scott> resultados2 = em.createQuery(
            "SELECT e FROM Scott e WHERE e.deptno = :depto", Scott.class)
            .setParameter("depto", 20)
            .getResultList();
        for (Scott emp : resultados2) {
            System.out.println(emp.getEname() + " - " + emp.getJob());
        }
        
        // Consulta 3: ORDER BY
        System.out.println("\n3. Empleados ordenados por salario descendente:");
        List<Scott> resultados3 = em.createQuery(
            "SELECT e FROM Scott e ORDER BY e.sal DESC", Scott.class)
            .getResultList();
        for (Scott emp : resultados3) {
            System.out.println(emp.getEname() + ": " + emp.getSal());
        }
        
        // Consulta 5: BETWEEN
        System.out.println("\n4. Empleados con salario entre 1000 y 1200:");
        List<Scott> resultados4 = em.createQuery(
            "SELECT e FROM Scott e WHERE e.sal BETWEEN :min AND :max", Scott.class)
            .setParameter("min", 1000)
            .setParameter("max", 1200)
            .getResultList();
        for (Scott emp : resultados4) {
            System.out.println(emp.getEname() + ": " + emp.getSal());
        }
        
        // Consulta 6: Funciones de agregación
        System.out.println("\n5. Estadísticas de salarios:");
        Long count = em.createQuery("SELECT COUNT(e) FROM Scott e", Long.class).getSingleResult();
        Double avg = em.createQuery("SELECT AVG(e.sal) FROM Scott e", Double.class).getSingleResult();
        Integer sum = em.createQuery("SELECT SUM(e.sal) FROM Scott e", Integer.class).getSingleResult();
        Integer max = em.createQuery("SELECT MAX(e.sal) FROM Scott e", Integer.class).getSingleResult();
        Integer min = em.createQuery("SELECT MIN(e.sal) FROM Scott e", Integer.class).getSingleResult();
        
        System.out.println("Total empleados: " + count);
        System.out.println("Salario promedio: " + String.format("%.2f", avg));
        System.out.println("Suma de salarios: " + sum);
        System.out.println("Salario máximo: " + max);
        System.out.println("Salario mínimo: " + min);
    }
}