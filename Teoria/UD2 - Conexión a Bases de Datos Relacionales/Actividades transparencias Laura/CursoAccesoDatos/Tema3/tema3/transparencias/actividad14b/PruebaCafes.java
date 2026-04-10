package tema3.transparencias.actividad14b;

/**
 * Clase para probar la clase cafes
 * 
 */

public class PruebaCafes {

	public static void main(String[] args) {
		try {

			Cafes miCafe = new Cafes();
			System.out.println("\nContents of CAFES table:");
			miCafe.verTabla();
			
			//Observa como al llamar por segunda vez a verTabla los precios en BD se han modificado
			//Esta actualización se hace utilizando un ResultSet "CONCUR_UPDATABLE" en modificarPrecio
			miCafe.modificarPrecio(2);
			miCafe.verTabla();
		} catch (MercadoException e) {
			/*
			 * En la GUI o en la clase con la que interacciona el usuario
			 * capturamos las excepciones de alto nivel de nuestra apliación e
			 * informamos correctamente al usuario
			 */
			System.out.println("Lo sentimos ocurrión un error en la apliación"
					+ e.getMessage());

		}

	}
}
