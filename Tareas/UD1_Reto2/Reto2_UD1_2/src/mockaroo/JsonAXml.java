package mockaroo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import java.io.FileWriter;

public class JsonAXml {
    public static void main(String[] args) {
        System.out.println("Convertir JSON a XML");
        
        try {
            // JSON de ejemplo (podría leer de datos.json)
            String json = "[{\"id\":1,\"nombre\":\"Ana\",\"email\":\"ana@mail.com\"}," +
                         "{\"id\":2,\"nombre\":\"Luis\",\"email\":\"luis@mail.com\"}]";
            
            // Convertir
            JSONArray array = new JSONArray(json);
            String xml = XML.toString(array, "personas");
            
            System.out.println("XML resultante:");
            System.out.println(xml);
            
            // Guardar
            try (FileWriter fw = new FileWriter("resultado.xml")) {
                fw.write(xml);
                System.out.println("\nGuardado en resultado.xml");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}