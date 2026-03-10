package mockaroo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class MockarooBidireccional {
    public static void main(String[] args) {
        System.out.println("Conversiones Mockaroo");
        
        // 1. JSON a XML
        String json = "[{\"id\":1,\"nombre\":\"Carlos\"},{\"id\":2,\"nombre\":\"Elena\"}]";
        JSONArray array = new JSONArray(json);
        String xml = XML.toString(array, "personas");
        
        System.out.println("1. JSON a XML:");
        System.out.println(xml);
        
        // 2. XML a JSON
        String xmlSimple = "<personas><persona><id>3</id><nombre>Pedro</nombre></persona></personas>";
        JSONObject obj = XML.toJSONObject(xmlSimple);
        
        System.out.println("\n2. XML a JSON:");
        System.out.println(obj.toString(2));
        
        System.out.println("\nListo. JSON <-> XML funcionando.");
    }
}