package com.amanecer.seguros.persistencia;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import com.amanecer.seguros.modelo.Asistencia;

import com.amanecer.seguros.persistencia.Utilidades;


public class AsistenciaDAOImpl extends GenericDAOImpl<Asistencia,Integer> implements  AsistenciaDAO {
	
	public List<Asistencia> findByImporteMayorQue(int importe) {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();	 
		TypedQuery<Asistencia> q = em.createQuery(
				"select a " +
				"from Asistencia a " +
				"where a.importe >= :importe", Asistencia.class);
		q.setParameter( "importe", BigDecimal.valueOf(importe) );
		return q.getResultList();
	}

	public List<Object[]> findByImporteRango(int importe1, int importe2) {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();	 
		TypedQuery<Object[]> q = em.createQuery(
				"select a.id " +
				"from Asistencia a " +
				"where a.importe between :importe1 and :importe2", Object[].class);
		q.setParameter( "importe1", BigDecimal.valueOf(importe1) );
		q.setParameter( "importe2", BigDecimal.valueOf(importe2) );
		return q.getResultList();
	}
	
	public BigDecimal sumaTodosImportes() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		TypedQuery<BigDecimal> q = em.createQuery(
				"select SUM(a.importe) " +
				"from Asistencia a ", BigDecimal.class);
		return q.getSingleResult();
	}

	public Double saldoMedio() {
		EntityManager em = Utilidades.getEntityManagerFactory().createEntityManager();
		TypedQuery<Double> q = em.createQuery(
				"select AVG(a.importe) " +
				"from Asistencia a ", Double.class);
		return q.getSingleResult();
	}

}
