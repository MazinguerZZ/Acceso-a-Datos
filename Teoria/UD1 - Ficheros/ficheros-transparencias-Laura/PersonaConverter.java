package transparencias;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @descrition
 * @author Laura
 * @date 6/5/2015
 * @version 1.0
 * @license GPLv3
 */

public class PersonaConverter implements Converter{

	//Definimos la clase que puede convertir
	public boolean canConvert(Class clazz) {
		return clazz.equals(Persona.class);
	}

	
	/**
	 * Definimos como convertir un objeto Persona a XML
	 * @param value
	 * @param writer donde escribir los datos de salida
	 * @param context nos permite aplicar otros convertidores cuando el objeto tiene atributos que a su vez 
	 * requieren un convertidor personalizado ver ComplexConverter en http://xstream.codehaus.org/converter-tutorial.html
	 */
	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Persona persona = (Persona) value;
		writer.addAttribute("apellidos", persona.getApellido());
		//Podemos abrir y cerrar nodos tantas veces como haga falta,pero sin olvidar siempre cerrarlo
		writer.startNode("fullname");
		writer.setValue(persona.getNombre());
		writer.endNode();
	}

	
	/**
	 * Definimos como restaurar un objeto Persona a partir de un XML
	 * @param reader donde leer los datos de entrada
	 * @param context nos permite aplicar otros convertidores cuando el objeto tiene atributos que a su vez 
	 * requieren un convertidor personalizado ver ComplexConverter en http://xstream.codehaus.org/converter-tutorial.html
	 * @return
	 */
	public Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext context) {
		Persona persona = new Persona();
		//Nos podemos mover abajo y arriba en el árbol de nodos según necesitemos
		persona.setApellido(reader.getAttribute("apellidos"));
		reader.moveDown();
		persona.setNombre(reader.getValue());
		reader.moveUp();
		return persona;
	}

}
