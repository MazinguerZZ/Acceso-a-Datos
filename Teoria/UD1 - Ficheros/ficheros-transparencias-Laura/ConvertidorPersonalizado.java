package transparencias;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *  @descrition
 *	@author Laura
 *  @date 6/5/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class ConvertidorPersonalizado {
	 public static void main(String[] args) {
         Persona persona = new Persona();
         persona.setNombre("Guilherme");
         persona.setApellido("Garcia");

         XStream xStream = new XStream(new DomDriver());
         //Registramos el convertidor Personalizado
         xStream.registerConverter(new PersonaConverter());
         xStream.alias("persona", Persona.class);
         System.out.println(xStream.toXML(persona));
 }

}


