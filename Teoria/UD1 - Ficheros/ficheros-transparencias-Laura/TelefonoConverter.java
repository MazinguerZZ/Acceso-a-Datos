package transparencias;

import com.thoughtworks.xstream.converters.SingleValueConverter;

/**
 *  @descrition Definición de convertidor para clase Telefono
 *	@author Laura
 *  @date 6/5/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class TelefonoConverter implements SingleValueConverter {

    //Indicamos al convertidor como convertir a String un objeto Telefono
	public String toString(Object obj) {
            return new Integer(((Telefono) obj).getNumero()).toString();
    }

    //Indicamos al convertidor como obtener un objeto Telefono a partir de un String
	public Object fromString(String numero) {
    	    Telefono tel=new Telefono();
    	    tel.setNumero(new Integer(numero));
            return tel;
    }

    //Indicamos al convertidor que tipos puede convertir
    public boolean canConvert(Class type) {
            return type.equals(Telefono.class);
    }

}




