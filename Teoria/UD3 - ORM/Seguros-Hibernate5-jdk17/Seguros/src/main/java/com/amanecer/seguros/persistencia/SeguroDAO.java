package com.amanecer.seguros.persistencia;

import java.util.List;

import com.amanecer.seguros.modelo.Seguro;

public interface SeguroDAO extends GenericDAO<Seguro,Integer> {

	List<Object[]> getNifNombre();

	String nifByNombre(String nombre, String ape1, String ape2);

	Long numSeguros();

	List<Object[]> datosSeguro();

	List<Object[]> alergiaSeguro();

}
