
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AlumnosJPA {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadAlumnos");
		EntityManager em = emf.createEntityManager();

		Alumno yo = new Alumno("11111222H", "Luis", "Nano", 12659);

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
