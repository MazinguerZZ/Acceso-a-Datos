package org.dam2.instituto;

import java.util.List;
import jakarta.persistence.*;

public class AppInstituto {

	public static void main(String[] args) {
		
		// Crear EntityManager para trabajar con la base de datos
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Instituto");
		EntityManager em = emf.createEntityManager();
		
		try {
			System.out.println("=== EXAMEN DAM2 ===\n");
			
			// CRUD
			
			// 1. Crear un alumno, asociarlo a un grupo y a un módulo
			System.out.println("1. Crear alumno, asociar a grupo y módulo:");
			em.getTransaction().begin();
			
			// Buscar el grupo DAM2 en la base de datos
			Grupo grupo = em.find(Grupo.class, "DAM2");
			if (grupo == null) {
				// Si no existe, crear uno nuevo
				grupo = new Grupo();
				grupo.setNombre("DAM2");
				grupo.setUbicacion("Aula DAM2");
				em.persist(grupo);
			}
			
			// Buscar el módulo ADAT en la base de datos
			Modulo modulo = em.find(Modulo.class, "ADAT");
			if (modulo == null) {
				// Si no existe, crear uno nuevo
				modulo = new Modulo();
				modulo.setId("ADAT");
				modulo.setNombre("Acceso a datos");
				em.persist(modulo);
			}
			
			// Crear un nuevo alumno
			Alumno alumno = new Alumno();
			alumno.setNia("999");
			alumno.setNombre("Adrian");
			alumno.setFecha("2006-05-14");
			alumno.setGrupo(grupo);
			alumno.getModulos().add(modulo);
			
			em.persist(alumno);
			em.getTransaction().commit();
			System.out.println("Alumno creado");
			
			// 2. Crear un profesor y asociarlo a un módulo
			System.out.println("\n2. Crear profesor y asociar a módulo:");
			em.getTransaction().begin();
			
			Profesor profesor = new Profesor();
			profesor.setDni("88888888X");
			profesor.setNombre("Luis");
			profesor.setEspecialidad("Informática");
			
			// Asignar el profesor al módulo ADAT
			modulo.setProfesor(profesor);
			profesor.getModulos().add(modulo);
			
			em.persist(profesor);
			em.getTransaction().commit();
			System.out.println("Profesor creado y asociado a módulo ADAT");
			
			// 3. Modificar el nombre de un alumno y de un profesor
			System.out.println("\n3. Modificar nombres:");
			em.getTransaction().begin();
			
			// Buscar y modificar el alumno recién creado
			Alumno alumnoMod = em.find(Alumno.class, "999");
			if (alumnoMod != null) {
				alumnoMod.setNombre("Pepe Modificado");
				System.out.println("Alumno modificado");
			}
			
			// Buscar y modificar el profesor con DNI "1"
			Profesor profesorMod = em.find(Profesor.class, "1");
			if (profesorMod != null) {
				profesorMod.setNombre("Javier Modificado");
				System.out.println("Profesor modificado");
			}
			
			em.getTransaction().commit();
			
			// 4. Borrar un alumno, un profesor, un módulo y un grupo (daw2)
			System.out.println("\n4. Borrar elementos:");
			em.getTransaction().begin();
			
			// Borrar el alumno creado anteriormente
			Alumno alumnoBorrar = em.find(Alumno.class, "999");
			if (alumnoBorrar != null) {
				// Desvincular al alumno de todos sus módulos
				for (Modulo m : alumnoBorrar.getModulos()) {
					m.getAlumnos().remove(alumnoBorrar);
				}
				alumnoBorrar.getModulos().clear();
				em.remove(alumnoBorrar);
				System.out.println("Alumno borrado");
			}
			
			// Borrar el profesor creado anteriormente
			Profesor profesorBorrar = em.find(Profesor.class, "88888888X");
			if (profesorBorrar != null) {
				// Desvincular al profesor de todos sus módulos
				for (Modulo m : profesorBorrar.getModulos()) {
					m.setProfesor(null);
				}
				profesorBorrar.getModulos().clear();
				
				// Si el profesor era tutor de algún grupo, desvincularlo
				if (profesorBorrar.getGrupo() != null) {
					profesorBorrar.getGrupo().setTutor(null);
				}
				
				em.remove(profesorBorrar);
				System.out.println("Profesor borrado");
			}
			
			// Actualizar el nombre del módulo ADAT
			Modulo moduloActualizar = em.find(Modulo.class, "ADAT");
			if (moduloActualizar != null) {
				moduloActualizar.setNombre("Acceso a datos Actualizado");
				System.out.println("Módulo actualizado");
			}
			
			// Borrar el grupo DAW2 si existe
			Grupo grupoBorrar = em.find(Grupo.class, "DAW2");
			if (grupoBorrar != null) {
				// Desvincular a todos los alumnos del grupo
				for (Alumno a : grupoBorrar.getAlumnos()) {
					a.setGrupo(null);
				}
				grupoBorrar.getAlumnos().clear();
				
				// Desvincular al tutor del grupo
				if (grupoBorrar.getTutor() != null) {
					grupoBorrar.getTutor().setGrupo(null);
					grupoBorrar.setTutor(null);
				}
				
				em.remove(grupoBorrar);
				System.out.println("Grupo DAW2 borrado");
			}
			
			em.getTransaction().commit();
			
			// CONSULTAS
			
			System.out.println("\n=== CONSULTAS ===");
			
			// 1. Listado de todos los alumnos, profesores, grupos y módulos (por separado)
			System.out.println("\n1. Todos los alumnos:");
			List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
			for (Alumno a : alumnos) {
				System.out.println("  - " + a.getNombre() + " (NIA: " + a.getNia() + ")");
			}
			
			System.out.println("\n2. Todos los profesores:");
			List<Profesor> profesores = em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
			for (Profesor p : profesores) {
				System.out.println("  - " + p.getNombre() + " (DNI: " + p.getDni() + ")");
			}
			
			System.out.println("\n3. Todos los grupos:");
			List<Grupo> grupos = em.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
			for (Grupo g : grupos) {
				System.out.println("  - " + g.getNombre() + " (" + g.getUbicacion() + ")");
			}
			
			System.out.println("\n4. Todos los módulos:");
			List<Modulo> modulos = em.createQuery("SELECT m FROM Modulo m", Modulo.class).getResultList();
			for (Modulo m : modulos) {
				System.out.println("  - " + m.getNombre() + " (ID: " + m.getId() + ")");
			}
			
			// 2. Listado de todos los alumnos del grupo DAM2
			System.out.println("\n5. Alumnos del grupo DAM2:");
			List<Alumno> dam2 = em.createQuery("SELECT a FROM Alumno a WHERE a.grupo.nombre = 'DAM2'", Alumno.class).getResultList();
			for (Alumno a : dam2) {
				System.out.println("  - " + a.getNombre() + " (NIA: " + a.getNia() + ")");
			}
			
			// 3. Listado de todos los módulos impartidos por el profesor David
			System.out.println("\n6. Módulos impartidos por David:");
			List<Modulo> david = em.createQuery("SELECT m FROM Modulo m WHERE m.profesor.nombre = 'David'", Modulo.class).getResultList();
			for (Modulo m : david) {
				System.out.println("  - " + m.getNombre() + " (ID: " + m.getId() + ")");
			}
			
			// 4. Listado de todos los profesores tutores de algún grupo
			System.out.println("\n7. Profesores tutores:");
			List<Profesor> tutores = em.createQuery("SELECT p FROM Profesor p WHERE p.grupo IS NOT NULL", Profesor.class).getResultList();
			for (Profesor p : tutores) {
				System.out.println("  - " + p.getNombre() + " (DNI: " + p.getDni() + ")");
			}
			
			// 5. Número de alumnos que tienen el módulo ADAT
			System.out.println("\n8. Número de alumnos con ADAT:");
			Long adat = em.createQuery("SELECT COUNT(a) FROM Alumno a JOIN a.modulos m WHERE m.id = 'ADAT'", Long.class).getSingleResult();
			System.out.println("  Total: " + adat);
			
			System.out.println("\n=== EXAMEN COMPLETADO ===");
			
		} catch (Exception e) {
			// Manejo de errores
			System.out.println("ERROR: " + e.getMessage());
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			// Cerrar recursos
			em.close();
			emf.close();
		}
	}
}