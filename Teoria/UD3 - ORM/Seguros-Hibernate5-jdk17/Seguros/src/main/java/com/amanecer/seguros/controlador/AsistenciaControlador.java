package com.amanecer.seguros.controlador;

import java.math.BigDecimal;
import java.util.List;

import com.amanecer.seguros.modelo.Asistencia;
import com.amanecer.seguros.persistencia.AsistenciaDAO;
import com.amanecer.seguros.persistencia.AsistenciaDAOImpl;

public class AsistenciaControlador {

	private final AsistenciaDAO asistenciaDAO = new AsistenciaDAOImpl();

	public void ej4(int importe) {
		List<Asistencia> asistencias = asistenciaDAO.findByImporteMayorQue(importe);
		for (Asistencia a: asistencias) {
			System.out.println("[ej4] Asistencia: " + a.getId());
		}
	}
	
	public void ej5 (int importe1, int importe2) {
		List<Object[]> asistencias = asistenciaDAO.findByImporteRango(importe1, importe2);	
		for (Object a: asistencias) {
			System.out.println("[ej5] AsistenciaId: " + a);
		}
	}
	
	public void ej6 () {
		BigDecimal suma = asistenciaDAO.sumaTodosImportes();	
		System.out.println("[ej6] Suma Importes asistencias: " + suma);
	}
	
	public void ej7 () {
		Double media = asistenciaDAO.saldoMedio();	
		System.out.println("[ej7] Saldo medio asistencias: " + media);
	}

}
