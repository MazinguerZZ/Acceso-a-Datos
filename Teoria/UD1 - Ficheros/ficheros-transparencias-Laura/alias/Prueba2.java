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

public class Prueba2 {

	public static void main(String[] args) {
		Blog teamBlog = new Blog(new Autor("Guilherme Silveira"));
		teamBlog.add(new Entrada("first", "My first blog entry"));
		teamBlog.add(new Entrada("tutorial",
				"Today we have developed a tutorial"));

		XStream xstream = new XStream(new DomDriver());
		//Definimos alias de clases
		xstream.alias("blog", Blog.class);
		xstream.alias("entrada", Entrada.class);
		
		//Definimos alias de atributo
		xstream.aliasField("autor", Blog.class, "escritor");
		
		//Omitimos tag raiz para la colección
		 xstream.addImplicitCollection(Blog.class, "entradas");
		 
		System.out.println(xstream.toXML(teamBlog));
	}
}
