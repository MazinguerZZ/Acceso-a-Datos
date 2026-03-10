
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class tablasScott1_2 {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadScott");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			System.out.println("LECTURA: ");

			List<Scott> scott = em.createQuery("SELECT a FROM Scott a", Scott.class).getResultList();

			for (Scott a : scott) {
				System.out.println("EMPNO: " + a.getEmpno() + ", ENAME: " + a.getEname() + ", JOB: " + a.getJob() + ", MGR: " + a.getMgr()
				+ ", SAL: " + a.getSal() + ", COMM: " + a.getComm() + ", DEPTNO: " + a.getDeptno());
			}

			em.getTransaction().commit();
			
			
			System.out.println("\nUPDATE: ");
			
			em.getTransaction().begin();
			Scott scottUpdate = new Scott();
			scottUpdate = em.find(Scott.class, 7001L);
			scottUpdate.setSal(15000);
			em.merge(scottUpdate);
			em.getTransaction().commit();
			
			for (Scott a : scott) {
				System.out.println("EMPNO: " + a.getEmpno() + ", ENAME: " + a.getEname() + ", JOB: " + a.getJob() + ", MGR: " + a.getMgr()
				+ ", SAL: " + a.getSal() + ", COMM: " + a.getComm() + ", DEPTNO: " + a.getDeptno());
			}
			
			
			System.out.println("\nDELETE: ");
			
            em.getTransaction().begin(); // Begin a new transaction
            for (Scott scottDelete : scott) {
                em.remove(scottDelete);
            }
            em.getTransaction().commit();
			System.out.println("Se han eliminado todos los campos");
			
			
			
			System.out.println("\nCreate: ");
            em.getTransaction().begin();

            // Ejemplo: crear nuevos empleados si quieres
            Scott nuevoEmpleado = new Scott(7004, "Laura", "ANALYST", 7604, 1400, 900, 20);
            em.persist(nuevoEmpleado);

            em.getTransaction().commit();

            // Leer todos para verificar creación
            List<Scott> scottFinal = em.createQuery("SELECT a FROM Scott a", Scott.class).getResultList();
            for (Scott a : scottFinal) {
                System.out.println("EMPNO: " + a.getEmpno() + ", ENAME: " + a.getEname() + ", JOB: " + a.getJob() +
                                   ", MGR: " + a.getMgr() + ", SAL: " + a.getSal() + ", COMM: " + a.getComm() +
                                   ", DEPTNO: " + a.getDeptno());
            }
            
			System.out.println("\nTabla final: ");

			List<Scott> scott2 = em.createQuery("SELECT a FROM Scott a", Scott.class).getResultList();

			for (Scott a : scott2) {
				System.out.println("EMPNO: " + a.getEmpno() + ", ENAME: " + a.getEname() + ", JOB: " + a.getJob() + ", MGR: " + a.getMgr()
				+ ", SAL: " + a.getSal() + ", COMM: " + a.getComm() + ", DEPTNO: " + a.getDeptno());
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