package tema3.transparencias;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  Clase que prueba la conexión a una BD utilizando la clase Driver Manager
 *	
 */

public class PruebaUtilidades {
	
	 /* TODO: Para que funcione hay que instalar el driver mysql
	  * 1. Descargar el driver y copiar el .jar a una carpeta lib/conectores/ por ejemplo
	  * 3. Ir a propiedades del proyecto --> Java Build Path --> libaries --> Add External Jar --> Incluir el .jar anterior
	  * 4. Al ejecutar en Run as --> Run Configuration --> arguments --> Tema3/tema3/transparencias/mysql-properties.xml
	  * 5. En mysql-properties.xml ir cambiando el nombre de la BD en los ejercicios según la que queramos usar
	  */
	 public static void main(String[] args) {
		    Utilidades myConexionDriverManager;
		    Connection myConnection = null;
		    if (args[0] == null) {
		      System.err.println("Properties file not specified at command line");
		      return;
		    } else {
		      try {
		        System.out.println("Reading properties file " + args[0]);
		        myConexionDriverManager = new Utilidades(args[0]);
		      } catch (Exception e) {
		        System.err.println("Problem reading properties file " + args[0]);
		        e.printStackTrace();
		        return;
		      }
		    }

		    try {
		      myConnection = myConexionDriverManager.getConnection();   

		    } catch (SQLException e) {
		    	e.printStackTrace(System.err);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    } finally {
		      Utilidades.closeConnection(myConnection);
		    }

		  }

}


