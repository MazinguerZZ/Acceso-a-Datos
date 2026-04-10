package tema3.transparencias.actividad12;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cafes {

	// Consultas a realizar en BD
	private static final String SELECT_CAFES_QUERY = "select CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from CAFES";
	private static final String SEARCH_CAFE_QUERY = "select * from CAFES WHERE CAF_NOMBRE= ?";
	private static final String INSERT_CAFE_QUERY="insert into CAFES values (?,?,?,?,?)";
	private static final String DELETE_CAFE_QUERY = "delete from CAFES WHERE CAF_NOMBRE = ?";

	/**
	 * Método que muestra por pantalla los datos de la tabla cafes
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public void verTabla() {
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
				String coffeeName = rs.getString("CAF_NOMBRE");
				int supplierID = rs.getInt("PROV_ID");
				float PRECIO = rs.getFloat("PRECIO");
				int VENTAS = rs.getInt("VENTAS");
				int total = rs.getInt("TOTAL");
				System.out.println(coffeeName + ", " + supplierID + ", "
						+ PRECIO + ", " + VENTAS + ", " + total);
			}
			
			
		} catch (IOException e){
			//Error al leer propiedades
			//En una aplicación real, escribo en el log y delego
			System.err.println(e.getMessage());
        } catch (SQLException sqle) {
            //En una aplicación real, escribo en el log y delego
            System.err.println(sqle.getMessage());
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
            }
        }

	}
	
	/**
	 * Método que busca un cafe por nombre y muestra sus datos
	 *
	 * @param nombre
	 */
	public void buscar(String nombre) {
		/* Conexi�n a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		try {
			con = new Utilidades().getConnection();
			// Creación de la sentencia
			stmt = con.prepareStatement(SEARCH_CAFE_QUERY);
			stmt.setString(1,nombre);
			// Ejecución de la consulta y obtención de resultados en un
			// ResultSet
			 rs = stmt.executeQuery();

			// Recuperación de los datos del ResultSet
			if (rs.next()) {
				String coffeeName = rs.getString("CAF_NOMBRE");
				int supplierID = rs.getInt("PROV_ID");
				float PRECIO = rs.getFloat("PRECIO");
				int VENTAS = rs.getInt("VENTAS");
				int total = rs.getInt("TOTAL");
				System.out.println(coffeeName + ", " + supplierID + ", "
						+ PRECIO + ", " + VENTAS + ", " + total);
			}
			
			
		} catch (IOException e){
			//Error al leer propiedades
			//En una aplicación real, escribo en el log y delego
			System.err.println(e.getMessage());
        } catch (SQLException sqle) {
            //En una aplicación real, escribo en el log y delego
            System.err.println(sqle.getMessage());
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
            }
        }

	}
	
	
	/**
	 * Método para insertar una fila
	 * @param nombre
	 * @param provid
	 * @param precio
	 * @param ventas
	 * @param total
	 * @return
	 */
	public void insertar(String nombre, int provid, float precio, int ventas, int total) {
		/* Conexión a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		
		
		try {
			con = new Utilidades().getConnection();
			stmt = con.prepareStatement(INSERT_CAFE_QUERY);
			stmt.setString(1,nombre);
			stmt.setInt(2,provid);
			stmt.setFloat(3,precio);
			stmt.setInt(4,ventas);
			stmt.setInt(5,total);			
			// Ejecución de la inserción
			stmt.executeUpdate();
		
		} catch (IOException e){
			//Error al leer propiedades
			//En una aplicación real, escribo en el log y delego
			System.err.println(e.getMessage());
			
        } catch (SQLException sqle) {
            //En una aplicación real, escribo en el log y delego
            System.err.println(sqle.getMessage());
           
        } finally {
            try {
                //Liberamos todos los recursos pase lo que pase
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    Utilidades.closeConnection(con);
                }
            } catch (SQLException sqle) {
                //En una aplicación real, escribo en el log, no delego porque es error al liberar recursos  
            }
        }

	}
	
	
	/**
	 * Método para borrar una fila dado un nombre de café
	 * @param nombre
	 * @return
	 */
	public void borrar(String nombre) {
		/* Conexión a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
	
		
		try {
			con = new Utilidades().getConnection();
			// Creación de la sentencia
			stmt = con.prepareStatement(DELETE_CAFE_QUERY);
			stmt.setString(1,nombre);
			// Ejecución del borrado
			stmt.executeUpdate();
		
		} catch (IOException e){
			//Error al leer propiedades
			//En una aplicación real, escribo en el log y delego
			System.err.println(e.getMessage());
			
        } catch (SQLException sqle) {
            //En una aplicación real, escribo en el log y delego
            System.err.println(sqle.getMessage());
          
        } finally {
            try {
                //Liberamos todos los recursos pase lo que pase
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    Utilidades.closeConnection(con);
                }
            } catch (SQLException sqle) {
                //En una aplicación real, escribo en el log, no delego porque es error al liberar recursos  
            }
        }

	}
	
	
}
