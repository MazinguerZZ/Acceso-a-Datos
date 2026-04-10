package tema3.transparencias.actividad14b;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Cafes1 {

	// Consultas a realizar en BD
	private static final String SELECT_CAFES_QUERY = "select CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from NOEXISTE";
	

	/**
	 * Método que muestra por pantalla los datos de la tabla cafes
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public void verTabla() throws AccesoDatosException{
		/* Conexión a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		try {
			con = new Utilidades().getConnection();
			// Creación de la sentencia
			stmt = con.createStatement();
			// Ejecución de la consulta y obtención de resultados en un
			// ResultSet
			 rs = stmt.executeQuery(SELECT_CAFES_QUERY);

			// Recuperación de los datos del ResultSet
			while (rs.next()) {
				String coffeeName = rs.getString(1);
				int supplierID = rs.getInt(2);
				float PRECIO = rs.getFloat(3);
				int VENTAS = rs.getInt(4);
				int total = rs.getInt(5);
				System.out.println(coffeeName + ", " + supplierID + ", "
						+ PRECIO + ", " + VENTAS + ", " + total);
			}
			
			
		} catch (IOException e){
			//Error al leer propiedades
			//En una aplicación real, escribo en el log y delego
			System.err.println(e.getMessage());
			throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } catch (SQLException sqle) {
            //En una aplicación real, escribo en el log y delego
            //System.err.println(sqle.getMessage());
        	Utilidades.printSQLException(sqle);
        	throw new AccesoDatosException("Ocurrió un error al acceder a los datos");
        } finally {
            try {
                //Liberamos todos los recursos pase lo que pase
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    Utilidades.closeConnection(con);
                }
            } catch (SQLException sqle) {
                //En una aplicación real, escribo en el log, no delego porque es error al liberar recursos
            	Utilidades.printSQLException(sqle);
            }
        }

	}
}
