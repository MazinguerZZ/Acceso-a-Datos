package com.amanecer.seguros.persistencia;

import java.math.BigDecimal;
import java.util.List;

import com.amanecer.seguros.modelo.Asistencia;

public interface AsistenciaDAO extends GenericDAO<Asistencia,Integer> {

	List<Asistencia> findByImporteMayorQue(int importe);

	List<Object[]> findByImporteRango(int importe1, int importe2);

	BigDecimal sumaTodosImportes();

	Double saldoMedio();


}
