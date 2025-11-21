

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Prueba1JPA {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersonas");
		EntityManager em = emf.createEntityManager();
		
		Persona yo = new Persona("Pedro", 25);


		try {
			em.getTransaction().begin();
			em.persist(yo);
			em.getTransaction().commit();
			System.out.println("Todo ha ido bien");
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Ha sido un desastre");

		} finally {
			em.close();
			emf.close();
		}
	}
}
