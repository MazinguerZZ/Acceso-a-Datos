package transparencias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @descrition Clase ejemplo para ver como funcionan los convertidores
 *             predefinidos para colecciones en XStream
 * @author Laura
 * @date 6/5/2015
 * @version 1.0
 * @license GPLv3
 */

public class ConvertidoresPredefinidosColecciones {
	public static void main(String[] args) {
		ConvertidoresPredefinidosColecciones ejemplo = new ConvertidoresPredefinidosColecciones();
		ejemplo.runEjemplo();
	}

	public void runEjemplo() {
		XStream xStream = new XStream(new DomDriver());
		Colecciones classContainingCollection = new Colecciones();
		classContainingCollection.inicializar();
		String xml = xStream.toXML(classContainingCollection);
		System.out.println(xml);
	}
}

/**
 * Clase con varias Colecciones Java para probar convertidores predefinidos
 * 
 * @descrition
 * @author Laura
 * @date 26/3/2015
 * @version 1.0
 * @license GPLv3
 */
class Colecciones {

	String[] stringArray = new String[] { "StringA", "StringB", "StringC" };
	char[] charArray = new char[] { 'a', 'b', 'c', 'd', 'e' };
	List<String> listA = new ArrayList<String>();
	Map<String, String> mapA = new HashMap<String, String>();
	Properties properties = new Properties();
	List<String> singletonList;
	Map<String, String> singletonMap;

	TreeMap<String, String> treeMap = new TreeMap<String, String>();
	TreeSet<String> treeSet = new TreeSet<String>();

	enum testEnum {
		testA, testB
	};

	testEnum testeEnumValue = testEnum.testA;
	EnumMap<testEnum, String> testEnumMap = new EnumMap<Colecciones.testEnum, String>(
			testEnum.class);
	EnumSet<testEnum> testEnumSet = EnumSet.range(testEnum.testA,
			testEnum.testB);

	public void inicializar() {
		listA.add("testA");
		mapA.put("keyA", "ValueA");
		properties.put("propertyA", "valueA");
		treeMap.put("treeA", "valueA");
		treeMap.put("treeB", "valueB");
		treeSet.add("treeB");
		treeSet.add("treeA");
		singletonList = Collections.singletonList("singletonListA");
		singletonMap = Collections.singletonMap("key1", "value1");

		testEnumMap.put(testEnum.testA, "testEnumMapValue1");
		testEnumMap.put(testEnum.testB, "testEnumMapValue2");

	}
}
