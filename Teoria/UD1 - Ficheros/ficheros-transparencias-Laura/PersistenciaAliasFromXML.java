package transparencias;


import java.io.File;
import java.util.Iterator;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 * @descrition Primera clase de prueba de XStream;
 * @author Laura
 * @date 29/4/2015
 * @version 1.0
 * @license GPLv3
 */

public class PersistenciaAliasFromXML {

	public static void main(String[] args) {

		// Mucho cuidado con pasar un nombre de directorio existente a new File.
		// Si no existe, obtendremos NullPointerException
		// XStream no preparado para java.nio.file.Path
		XStream xstream = new XStream(new DomDriver());
		// Definimos alias de clases
		xstream.alias("Escritor", Autor.class);

		// Definimos alias de atributo
		xstream.aliasField("seudonimo", Autor.class, "nombre");
		XmlArrayList lista = new XmlArrayList(new FilePersistenceStrategy(
				new File("."), xstream));

		// Iteramos sobre los archivos xml y vamos construyendo el objeto
		for (Iterator it = lista.iterator(); it.hasNext();) {
			Autor autor = (Autor) it.next();

			System.out.println(autor.getNombre());

		}

	}

}
