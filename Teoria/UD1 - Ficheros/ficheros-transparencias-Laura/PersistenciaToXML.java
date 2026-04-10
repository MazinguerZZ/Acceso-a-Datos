package transparencias;


import java.io.File;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 *  @descrition Primera clase de prueba de XStream;
 *	@author Laura
 *  @date 29/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class PersistenciaToXML {

	public static void main(String[] args) {
		
		//Mucho cuidado con pasar un nombre de directorio existente a new File. Si no existe, obtendremos NullPointerException
		//XStream no preparado para java.nio.file.Path
		PersistenceStrategy strategy = new FilePersistenceStrategy(new File("."));
		// creates the list:
		XmlArrayList lista = new XmlArrayList(strategy);
		
		//Añadimos autores
		lista.add(new Autor("joe walnes"));
		lista.add(new Autor("joerg schaible"));
		lista.add(new Autor("mauro talevi"));
		lista.add(new Autor("guilherme silveira"));
		
		// Añadimos otro autor del que nos habíamos olvidado
		Autor mistake = new Autor("mama");
		lista.add(mistake);
		
		/*Si ahora comprobamos el directorio actual veremos que hay 5 archivos int@1.xml, int@2.xml, int@3.xml, int@4.xml, int@5.xml cada uno con
		  un xml correspondiente a un autor*/ 
		 

	}

}


