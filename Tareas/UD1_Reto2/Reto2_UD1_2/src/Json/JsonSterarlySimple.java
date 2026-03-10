package Json;

import org.json.JSONObject;
import org.json.JSONArray;

public class JsonSterarlySimple {
    public static void main(String[] args) {
        System.out.println("Ejemplo Sterarly (org.json)");
        
        // Crear JSON manual
        JSONObject libro1 = new JSONObject();
        libro1.put("titulo", "Cien años de soledad");
        libro1.put("autor", "Gabriel García Márquez");
        libro1.put("año", 1967);
        
        JSONObject libro2 = new JSONObject();
        libro2.put("titulo", "1984");
        libro2.put("autor", "George Orwell");
        libro2.put("año", 1949);
        
        JSONArray biblioteca = new JSONArray();
        biblioteca.put(libro1);
        biblioteca.put(libro2);
        
        System.out.println(biblioteca.toString(2));
    }
}