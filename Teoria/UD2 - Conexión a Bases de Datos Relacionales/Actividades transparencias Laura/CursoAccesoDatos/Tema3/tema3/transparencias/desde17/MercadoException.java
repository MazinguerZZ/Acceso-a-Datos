

package tema3.transparencias.desde17;
/**
 * 
 *  Clase Raíz para la jerarquia de Excepciones de mi aplicación
 *	
 */
public class MercadoException extends Exception{

    /**
	 * Necesario por implementar Serializable
	 */
	private static final long serialVersionUID = 6308847858962342271L;

	public MercadoException(String message) {
        super(message);
    }

}
