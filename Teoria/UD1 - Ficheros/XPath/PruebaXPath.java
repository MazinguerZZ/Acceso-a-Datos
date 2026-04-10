import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PruebaXPath {
	
	
	final static String URL_XML_TIEMPO = "https://www.aemet.es/xml/municipios_h/localidad_h_28079.xml";
	final static String FICHERO_XML_TIEMPO = "tiempo.xml";
	
	public static void main(String[] args) throws MalformedURLException {
		// URL urlTiempo = new URL(URL_XML_TIEMPO);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL_XML_TIEMPO); // URL_XML_TIEMPO //"inventory.xml"
			
			XPathFactory xpathfactory = XPathFactory.newInstance();
			XPath xpath = xpathfactory.newXPath();
			// XPathExpression expr = xpath.compile("//book[@year>2001]/title/text()");
				XPathExpression expr = xpath.compile("//estado_cielo/text()");

			
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;

			for (int i = 0; i < nodes.getLength(); i++) {

			  System.out.println(nodes.item(i).getNodeValue());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
