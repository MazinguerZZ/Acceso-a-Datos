package ud1.examen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class ControlUD1 {
	static final String FICHERO_RUTAS = "rutas.xml";
	static final String RUTA_FICHERO_HSV = "pokemon.hsv";
	static final String RUTA_FICHERO_XML = "pokemon.xml";
	static final String RUTA_FICHERO_JSON = "pokemon.json";
	static final String RUTA_FICHERO_CSV = "pokemon.csv";
	static final String RUTA_FICHERO_XML2 = "pokemon2.xml";
	static final String RUTA_FICHERO_JSON2 = "pokemon2.json";

	public static void main(String[] args) {
		List<Pokemon> lista1 = leeHsv(RUTA_FICHERO_HSV);
		guardaXml(RUTA_FICHERO_XML,lista1);
		guardaXml2(RUTA_FICHERO_XML2,lista1);
		guardaJson(RUTA_FICHERO_JSON,lista1);
		guardaJson2(RUTA_FICHERO_JSON2,lista1);
		List<Pokemon> lista2 = leeXml(RUTA_FICHERO_XML);
		List<Pokemon> lista3 = leeXml2(RUTA_FICHERO_XML2);
		List<Pokemon> lista4 = leeJson(RUTA_FICHERO_JSON);
		List<Pokemon> lista5 = leeJson2(RUTA_FICHERO_JSON2);
		
		// Comprobaciones (opcional)
		System.exit(0);  // borrar esta línea para hacer las comprobaciones:
		if (lista1.size()==800) {
			System.out.println("Parece que el apartado 1 puede estar bien");
		}
		if ((lista1.size() == lista2.size()) && lista2.containsAll(lista1)) {
			System.out.println("Apartados 2 y 6 probablemente bien");
		}
		if ((lista1.size() == lista3.size()) && lista3.containsAll(lista1)) {
			System.out.println("Apartados 3 y 7 probablemente bien");
		}
		if ((lista1.size() == lista4.size()) && lista4.containsAll(lista1)) {
			System.out.println("Apartados 4 y 8 probablemente bien");
		}
		if ((lista1.size() == lista5.size()) && lista5.containsAll(lista1)) {
			System.out.println("Apartados 5 y 9 probablemente bien");
		}
		
		// Apartado properties:
		Map<String,String> mapaRutas = new HashMap<String,String>();
		mapaRutas.put("RUTA_FICHERO_HSV",RUTA_FICHERO_HSV);
		mapaRutas.put("RUTA_FICHERO_XML",RUTA_FICHERO_XML);
		mapaRutas.put( "RUTA_FICHERO_XML2",RUTA_FICHERO_XML2);
		mapaRutas.put("RUTA_FICHERO_JSON",RUTA_FICHERO_JSON);
		mapaRutas.put("RUTA_FICHERO_JSON2",RUTA_FICHERO_JSON2);
		
		guardaPropertiesXml(FICHERO_RUTAS,mapaRutas);
		Map<String,String> mapaRutas2 = cargaPropertiesXml(FICHERO_RUTAS);
		
	}

	static List<Pokemon> leeHsv(final String RUTA) {
		return null; // quitar
	}

	static List<Pokemon> leeXml(final String RUTA) {
		return null; // quitar
	}

	static List<Pokemon> leeXml2(final String RUTA) {
		return null; // quitar
	}

	static List<Pokemon> leeJson(final String RUTA) {
		return null; // quitar
	}

	static List<Pokemon> leeJson2(final String RUTA) {
		return null; // quitar
	}

	static void guardaXml(final String RUTA, List<Pokemon> lista) {

	}

	static void guardaXml2(final String RUTA, List<Pokemon> lista) {

	}

	static void guardaJson(final String RUTA, List<Pokemon> lista) {

	}

	static void guardaJson2(final String RUTA, List<Pokemon> lista) {

	}

	static void guardaPropertiesXml(String RUTA,Map<String,String> rutas) 
	{
	
	}

	static Map<String,String> cargaPropertiesXml(String RUTA) {
		return null;
	}

}
