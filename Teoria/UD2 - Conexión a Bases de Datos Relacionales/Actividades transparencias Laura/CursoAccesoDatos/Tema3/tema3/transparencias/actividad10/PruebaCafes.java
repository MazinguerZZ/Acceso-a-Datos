package tema3.transparencias.actividad10;


public class PruebaCafes {

	public static void main(String[] args) {
	
			
			Cafes miCafe = new Cafes();
			System.out.println("\nContents of CAFES table:");
			//miCafe.verTabla();
			miCafe.buscar("Colombian");
			//El id de proveedor tiene que existir en la tabla proveedores
			miCafe.insertar("Nescafe", 49, new Float(4.99), 89, 94);
			//Comprobamos que se insertó
			miCafe.buscar("Nescafe");
			miCafe.borrar("Nescafe");
			
			//Comprobamos que se borró
			miCafe.verTabla();

	}
}
