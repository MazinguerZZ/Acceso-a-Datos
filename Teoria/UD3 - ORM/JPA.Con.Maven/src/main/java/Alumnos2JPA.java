
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Alumnos2JPA { 

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadAlumnos");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			// Consulta JPQL moderna
			List<Alumno> alumno = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();

			for (Alumno a : alumno) {
				System.out.println("DNI: " + a.getDNI() + "Nombre: " + a.getNombre() + "Apellidos: " + a.getApellidos() + ", CP: " + a.getCP());
			}

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
