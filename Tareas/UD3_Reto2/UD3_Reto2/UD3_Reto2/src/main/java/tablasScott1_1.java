import jakarta.persistence.*;

public class tablasScott1_1 {
    
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadScott");
        EntityManager em = emf.createEntityManager();

        Scott empleado1 = new Scott(7000L, "Alejandro", "ANALYST", 7600, 1000, 500, 20);
        Scott empleado2 = new Scott(7001L, "Pedro", "ANALYST", 7601, 1100, 600, 20);
        Scott empleado3 = new Scott(7002L, "Adrian", "ANALYST", 7602, 1200, 700, 20);
        Scott empleado4 = new Scott(7003L, "Jose", "ANALYST", 7603, 1300, 800, 20);

        try {
            em.getTransaction().begin();
            em.persist(empleado1);
            em.persist(empleado2);
            em.persist(empleado3);
            em.persist(empleado4);
            em.getTransaction().commit();
            System.out.println("Empleados creados correctamente con Hibernate");
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al crear empleados: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}