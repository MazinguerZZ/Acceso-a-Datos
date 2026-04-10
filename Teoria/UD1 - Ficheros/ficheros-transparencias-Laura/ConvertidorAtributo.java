package transparencias;

import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @descrition Ejemplo de como convertir un objeto en atributo XML
 * @author Laura
 * @date 6/5/2015
 * @version 1.0
 * @license GPLv3
 */

public class ConvertidorAtributo {

	public static void main(String[] args) {

		Telefono tel = new Telefono();
		tel.setCodigo(34);
		tel.setNumero(666666666);
		Persona2 persona = new Persona2();
		persona.setNombre("Pepe");
		persona.setApellido("Garcia");
		persona.setTelefono(tel);
		persona.setMayorEdad(true);
		persona.setPeso(new Float(45.5));
		persona.setFecha(new Date());

		XStream xstream = new XStream(new DomDriver());
		// Definimos alias de clases
		xstream.alias("persona", Persona2.class);		
		// Definimos alias de atributo
		xstream.aliasField("adulto", Persona2.class, "mayorEdad");
		// Un atributo de tipo primitivo pasa a ser atributo xml
		xstream.useAttributeFor(Persona2.class, "nombre");
		// Un atributo de tipo no primitivo requiere definir un convertidor para
		// ser atributo xml
		xstream.useAttributeFor(Persona2.class, "telefono");
		//Podemos definir alias para atributos xml
		xstream.aliasAttribute("tel", "telefono");
		// Registramos los convertidores
		xstream.registerConverter(new TelefonoConverter());
		xstream.registerConverter(new BooleanConverter("si", "no", false));
		xstream.registerConverter(new DateConverter("dd-MM-yyyy HH:mm:ss",new String[] {"dd-MM-yyyy HH:mm:ss .S a", "dd-MM-yyyy HH:mm:ssz", "dd-MM-yyyy HH:mm:ss z", "dd-MM-yyyy HH:mm:ssa" }));
		// Convertimos el objeto persona a xml invocando el método toXML
		String xml = xstream.toXML(persona);

		System.out.println(xml);

		// Reconstruimos un objeto persona a partir del XML generado invocando
		// el método fromXML
		Persona2 persona2 = (Persona2) xstream.fromXML(xml);

		System.out.println(persona2.toString());

	}
}
