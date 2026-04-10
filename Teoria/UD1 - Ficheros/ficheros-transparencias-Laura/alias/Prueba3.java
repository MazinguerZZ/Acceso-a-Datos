package transparencias.alias;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @descrition Clase para probar como se genera un xml a partir de clases de un paquete
 * @author Laura
 * @date 30/4/2015
 * @version 1.0
 * @license GPLv3
 */

public class Prueba3 {

	public static void main(String[] args) {
		Blog teamBlog = new Blog(new Autor("Guilherme Silveira"));
		teamBlog.add(new Entrada("first", "My first blog entry"));
		teamBlog.add(new Entrada("tutorial",
				"Today we have developed a tutorial"));

		XStream xstream = new XStream(new DomDriver());
		//Definimos alias de paquete
		xstream.aliasPackage("com.alias","transparencias.alias");
		 
		System.out.println(xstream.toXML(teamBlog));
	}
}
