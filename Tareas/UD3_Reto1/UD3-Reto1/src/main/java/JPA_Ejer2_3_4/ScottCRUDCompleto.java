package JPA_Ejer2_3_4;

import jakarta.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ScottCRUDCompleto {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadScott2");
        EntityManager em = emf.createEntityManager();
        
        Scanner scanner = new Scanner(System.in);
        
        try {
            while (true) {
                System.out.println("\n=== MENÚ CRUD SCOTT2 ===");
                System.out.println("1. CREATE - Crear datos iniciales");
                System.out.println("2. READ - Mostrar todos los datos");
                System.out.println("3. UPDATE - Modificar empleado");
                System.out.println("4. DELETE - Eliminar proyecto");
                System.out.println("7. Salir");
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
            
            // Crear habilidades
            Habilidad h1 = new Habilidad("Java", "Avanzado");
            Habilidad h2 = new Habilidad("SQL", "Intermedio");
            Habilidad h3 = new Habilidad("HTML", "Básico");
            
            // Crear proyectos
            Proyecto p1 = new Proyecto("Sistema Contable", "Desarrollo principal");
            Proyecto p2 = new Proyecto("App Web", "Aplicación web ventas");
            Proyecto p3 = new Proyecto("Base de Datos", "Migración BD");
            
            // Establecer relaciones
            p1.setEmpleado(emp1);
            p2.setEmpleado(emp1);
            p3.setEmpleado(emp2);
            
            emp1.getHabilidades().add(h1);
            emp1.getHabilidades().add(h2);
            emp2.getHabilidades().add(h2);
            emp2.getHabilidades().add(h3);
            emp3.getHabilidades().add(h3);
            
            // Persistir
            em.persist(emp1);
            em.persist(emp2);
            em.persist(emp3);
            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            
            em.getTransaction().commit();
            System.out.println("Datos creados exitosamente");
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al crear datos: " + e.getMessage());
        }
    }
    
    private static void readDatos(EntityManager em) {
        em.getTransaction().begin();
        
        try {
            // Leer empleados con sus proyectos
            List<Scott2> empleados = em.createQuery(
                "SELECT DISTINCT e FROM Scott2 e LEFT JOIN FETCH e.proyectos", Scott2.class)
                .getResultList();
            
            System.out.println("\n=== EMPLEADOS Y SUS PROYECTOS ===");
            for (Scott2 emp : empleados) {
                System.out.println("\n" + emp.getEname() + " (" + emp.getJob() + ")");
                System.out.println("  Salario: " + emp.getSal());
                System.out.println("  Proyectos: " + emp.getProyectos().size());
                for (Proyecto p : emp.getProyectos()) {
                    System.out.println("    - " + p.getNombre() + ": " + p.getDescripcion());
                }
                
                // Cargar habilidades por separado
                emp.getHabilidades().size(); // Esto fuerza la carga
                System.out.println("  Habilidades: " + emp.getHabilidades().size());
                for (Habilidad h : emp.getHabilidades()) {
                    System.out.println("    - " + h);
                }
            }
            
            em.getTransaction().commit();
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al leer datos: " + e.getMessage());
        }
    }
    
    private static void updateDatos(EntityManager em, Scanner scanner) {
        System.out.print("ID del empleado a modificar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        
        System.out.print("Nuevo salario: ");
        int nuevoSalario = scanner.nextInt();
        scanner.nextLine();
        
        em.getTransaction().begin();
        
        try {
            Scott2 emp = em.find(Scott2.class, id);
            if (emp != null) {
                int salarioAnterior = emp.getSal();
                emp.setSal(nuevoSalario);
                System.out.println("Empleado " + emp.getEname() + 
                                 " - Salario actualizado de " + salarioAnterior + 
                                 " a " + nuevoSalario);
            } else {
                System.out.println("Empleado no encontrado");
            }
            
            em.getTransaction().commit();
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar: " + e.getMessage());
        }
    }
    
    private static void deleteDatos(EntityManager em, Scanner scanner) {
        System.out.print("Nombre del proyecto a eliminar: ");
        String nombreProyecto = scanner.nextLine();
        
        em.getTransaction().begin();
        
        try {
            TypedQuery<Proyecto> query = em.createQuery(
                "SELECT p FROM Proyecto p WHERE p.nombre = :nombre", Proyecto.class);
            query.setParameter("nombre", nombreProyecto);
            
            List<Proyecto> proyectos = query.getResultList();
            
            if (!proyectos.isEmpty()) {
                Proyecto p = proyectos.get(0);
                em.remove(p);
                System.out.println("Proyecto eliminado: " + p.getNombre());
            } else {
                System.out.println("Proyecto no encontrado");
            }
            
            em.getTransaction().commit();
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }
}
