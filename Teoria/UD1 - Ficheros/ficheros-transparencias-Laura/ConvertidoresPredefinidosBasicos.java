package transparencias;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @descrition Ejemplo de funcionamiento de los convertidores predefinidos en
 *             XStream
 * @author Laura
 * @date 6/5/2015
 * @version 1.0
 * @license GPLv3
 */

public class ConvertidoresPredefinidosBasicos {

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException {
		ConvertidoresPredefinidosBasicos converter = new ConvertidoresPredefinidosBasicos();
		converter.convert();
	}

	/**
	 * Uso de convertidores predefinidos
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	private void convert() throws MalformedURLException, URISyntaxException {

		CamposBasicos basicConverterExample = new CamposBasicos();
		basicConverterExample.inicializar();

		XStream xStream = new XStream(new DomDriver());
		
		//Registramos un convertidor de boolean pero solo para el campo flag
		xStream.registerLocalConverter(CamposBasicos.class, "flag",
				BooleanConverter.BINARY);

		//Registramos un convertidor de Fechas con un formato especifico de fecha. Este se aplicará para todos lo campos de tipo Date
		DateConverter dateConverter = new DateConverter("dd-MM-yyyy HH:mm:ss",
				new String[] {}, TimeZone.getTimeZone("UTC"));
		xStream.registerConverter(dateConverter);

		//Para el resto de convertidores de tipo como no los personalizamos no hace falta registrarlos se aplican automáticamente
		String xml = xStream.toXML(basicConverterExample);

		System.out.println(xml);

		CamposBasicos basicConverterFromXML = (CamposBasicos) xStream
				.fromXML(xml);
		System.out.println(basicConverterFromXML);

	}

	/**
	 * Clase de prueba con campos de todo tipo de java para ver como funcionan los convertidores predefinidos
	 * @descrition
	 * @author Laura
	 * @date 26/3/2015
	 * @version 1.0
	 * @license GPLv3
	 */
	class CamposBasicos {
		BigDecimal bigDecimal = new BigDecimal(10000000000.0);
		BigInteger bigInteger = new BigInteger("1000000000");
		boolean flag = true;
		boolean flag2=false;
		byte byteA = 'a';
		char charA = 'a';
		Date date = new Date();
		Date date1 = new Date();
		Double doubleA = new Double(1000000000000.0);
		Float floatA = new Float(10000000000000f);
		int intA = 100;
		Long longA = new Long(100000);
		Object nullA = null;
		Short shortA = new Short((short) 1);
		StringBuffer stringBufferA = new StringBuffer("test");
		StringBuilder stringBuilderA = new StringBuilder().append("test");
		URI UriA = null;
		URL urlA = null;
		UUID uuidA = UUID.fromString("0000000a-000b-000c-000d-00000000000e");

		public void inicializar() throws URISyntaxException,
				MalformedURLException {
			UriA = new URI("file://C/work/fileA");
			urlA = new URL("http://www.google.com");
		}

		@Override
		public String toString() {
			return "CamposBasicos [bigDecimal=" + bigDecimal + ", bigInteger="
					+ bigInteger + ", flag=" + flag +  ", flag2=" + flag2 +", byteA=" + byteA
					+ ", charA=" + charA + ", date=" + date + ", date1=" + date1+ ", doubleA="
					+ doubleA + ", floatA=" + floatA + ", intA=" + intA
					+ ", longA=" + longA + ", nullA=" + nullA + ", shortA="
					+ shortA + ", stringBufferA=" + stringBufferA
					+ ", stringBuilderA=" + stringBuilderA + ", UriA=" + UriA
					+ ", urlA=" + urlA + ", uuidA=" + uuidA + "]";
		}

	}

}
