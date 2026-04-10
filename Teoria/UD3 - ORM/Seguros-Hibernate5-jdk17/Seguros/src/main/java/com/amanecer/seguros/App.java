package com.amanecer.seguros;

import jakarta.persistence.EntityManager;

import com.amanecer.seguros.controlador.AsistenciaControlador;
import com.amanecer.seguros.controlador.SeguroControlador;
import com.amanecer.seguros.persistencia.*;


public class App {
	   
	public static void main( String[] args ) {
		System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");
		EntityManager em = null; 	
			
		try {
				em = Utilidades.getEntityManagerFactory().createEntityManager();	
				
				SeguroControlador sc = new SeguroControlador();
				AsistenciaControlador ac = new AsistenciaControlador();
				
				sc.ej1();
				sc.ej2();
				sc.ej3("maria","garcia","lozano");
				ac.ej4(10000);
				ac.ej5(8000, 15000);
				ac.ej6();
				ac.ej7();
				sc.ej8();
				sc.ej9();
				sc.ej10();
				sc.ej11();
						
				
		} catch (Exception e ) {
			e.printStackTrace();
			if (em != null) {
					System.out.println("Se va a hacer rollback de la transacción");
					em.getTransaction().rollback();
			}
		} finally {
				if (em != null) {
					em.close();
				}
		}
		Utilidades.closeEntityManagerFactory();
	}
}
