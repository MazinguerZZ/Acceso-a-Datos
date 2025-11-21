package JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaJPA {

    public static void main(String[] args) {

        Persona yo = new Persona("Pedro", 25);
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadPersonas");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(yo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}