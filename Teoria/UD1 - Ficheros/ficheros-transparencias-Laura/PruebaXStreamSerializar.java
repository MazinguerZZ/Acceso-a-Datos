package transparencias;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 *  @descrition
 *	@author Laura
 *  @date 6/5/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class PruebaXStreamSerializar {
	
	private XStream xstream;
	
	public PruebaXStreamSerializar(){
		xstream = new XStream(new DomDriver());
		xstream.alias("alumno", Alumno.class);
		xstream.useAttributeFor(Alumno.class, "nombre");
		xstream.aliasField("cumple", Alumno.class, "anoNacimiento");
	}

	public void serializar(Alumno a, Path ruta) {

		try {
			// Creamos el archivo
			// InputStream y OutputStream de java.io nos permiten trabajar byte
			// a byte
			OutputStream ostream = Files.newOutputStream(ruta);
			// FileOutputStream fs = new FileOutputStream("agenda.txt");
			ObjectOutputStream os = xstream.createObjectOutputStream(ostream);
			// El método writeObject() serializa el objeto y lo escribe en el
			// archivo
			os.writeObject(a);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Alumno deserializar(Path ruta) {
		Alumno a2 = null;

		try {
			InputStream istream = Files.newInputStream(ruta);
			// FileOutputStream fs = new FileOutputStream("agenda.txt");
			ObjectInputStream os =  xstream.createObjectInputStream(istream);

			// El método readObject() recupera el objeto

			a2 = (Alumno) os.readObject();
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return a2;

	}
	
	public static void main(String[] args) {
		PruebaXStreamSerializar serializador=new PruebaXStreamSerializar();
		Path ruta = Paths.get("alumno.xml");
		serializador.serializar(new Alumno("juan", "garcia", 1987),ruta);
		Alumno a=serializador.deserializar(ruta);
		System.out.println(a.toString());	
        
       
	}

}


