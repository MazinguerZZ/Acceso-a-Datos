package transparencias;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

/**
 *  @descrition
 *	@author Laura
 *  @date 6/5/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class PruebaXStreamJSON {

	public static void main(String[] args) {
		Producto producto = new Producto(12, "Banana", new Float(23.00));
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());        
        xstream.alias("product", Producto.class);

        System.out.println(xstream.toXML(producto));	
        
       
	}

}


