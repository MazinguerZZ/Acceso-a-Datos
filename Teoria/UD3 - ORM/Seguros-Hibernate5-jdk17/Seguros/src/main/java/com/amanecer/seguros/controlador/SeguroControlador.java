package com.amanecer.seguros.controlador;

import java.util.List;

import com.amanecer.seguros.modelo.Seguro;
import com.amanecer.seguros.modelo.Asistencia;
import com.amanecer.seguros.persistencia.SeguroDAO;
import com.amanecer.seguros.persistencia.SeguroDAOImpl;

public class SeguroControlador {

	private final SeguroDAO seguroDAO = new SeguroDAOImpl();

	public void ej1 () {
		List<Seguro> seguros = seguroDAO.findAll();
		for (Seguro s: seguros) {   		
			System.out.println("[ej1] Seguro: " + s.toString());
		}
	}
	
	public void ej2 () {
		List<Object[]> seguros = seguroDAO.getNifNombre();	
		for (Object[] s: seguros) {
			System.out.println("[ej2] NIF: " + s[0] + ", Nombre: " + s[1]);
		}
	}
	
	public void ej3(String nombre, String ape1, String ape2) {
		String nif = seguroDAO.nifByNombre(nombre, ape1, ape2);
		System.out.println("[ej3] El NIF encontrado es: "+ nif);
	}
	
	public void ej8() {
		Long numSeguros = seguroDAO.numSeguros();
		System.out.println("[ej8] El numero de seguros es: "+ numSeguros);
	}
	
	public void ej9 () {
		List<Object[]> seguros = seguroDAO.datosSeguro();	
		for (Object[] s: seguros) {
			System.out.println("[ej9] Seguro [Nombre: " + s[0] + ", NIF: " + s[1] + "] Asistencias: " + s[2]);
		}
	}
	
	public void ej10 () {
		List<Object[]> seguros = seguroDAO.alergiaSeguro();	
		for (Object[] s: seguros) {
			System.out.println("[ej10] Seguro: " + s[0] + " Alergia: " + s[1]);
		}
	}
	
	public void ej11 () {
		List<Seguro> seguros = seguroDAO.findAll();
		for (Seguro s: seguros) {   		
			System.out.println("[ej11] Seguro: " + s.toString());
			for (Asistencia a: s.getAsistencias()) {
				System.out.println("[ej11] Asistencia:" + a.getId());
			}
		}
	}
	
}
