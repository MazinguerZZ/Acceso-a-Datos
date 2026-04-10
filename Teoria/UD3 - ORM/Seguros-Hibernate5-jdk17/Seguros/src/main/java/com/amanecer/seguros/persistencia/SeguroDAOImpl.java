package com.amanecer.seguros.persistencia;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import com.amanecer.seguros.modelo.Seguro;

import com.amanecer.seguros.persistencia.Utilidades;

public class SeguroDAOImpl extends GenericDAOImpl<Seguro,Integer> implements  SeguroDAO {
	
	public List<Object[]> getNifNombre() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		TypedQuery<Object[]> q = em.createQuery(
				"select s.nif, s.nombre " +
				"from Seguro s ", Object[].class );
		return q.getResultList();
	}
	
	public String nifByNombre(String nombre, String ape1, String ape2) {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		TypedQuery<String> q = em.createQuery(
				"select s.nif " +
				"from Seguro s " +
				"where s.nombre = :nombre and s.ape1 = :ape1 and s.ape2 = :ape2", String.class);
		q.setParameter( "nombre", nombre );
		q.setParameter( "ape1", ape1 );
		q.setParameter( "ape2", ape2 );
		return q.getSingleResult();
	}
	
	public Long numSeguros() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		TypedQuery<Long> q = em.createQuery(
				"select count(*) " +
				"from Seguro s ", Long.class);
		return q.getSingleResult();
	}

	
	public List<Object[]> datosSeguro() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();	 
		TypedQuery<Object[]> q = em.createQuery(
				"select s.nombre, s.nif, count(a) " +
				"from Seguro s " +
				"left join s.asistencias a " +
				"group by s.nombre, s.nif", Object[].class);
		return q.getResultList();
	}
	
	public List<Object[]> alergiaSeguro() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();	 
		TypedQuery<Object[]> q = em.createQuery(
				"select s.id, e.nombreAlergia " +
				"from Seguro s " +
				"left join s.enfermedad e", Object[].class);
		return q.getResultList();
	}
}
