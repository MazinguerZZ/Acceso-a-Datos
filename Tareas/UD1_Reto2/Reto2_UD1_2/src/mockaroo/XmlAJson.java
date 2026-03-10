package mockaroo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlAJson {
    
    public static void main(String[] args) {
        System.out.println("CONVERSION XML A JSON");
        
        try {
            // 1. Leer el archivo XML desde el classpath
            System.out.println("1. Leyendo archivo XML desde recursos...");
            
            InputStream inputStream = XmlAJson.class.getResourceAsStream("dataset.xml");
            
            if (inputStream == null) {
                System.out.println("ERROR: No se encuentra dataset.xml en el classpath");
                System.out.println("El archivo debe estar en: Reto2/mockaroo/dataset.xml dentro de src");
                return;
            }
            
            String xmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            inputStream.close();
            
            System.out.println("   Archivo encontrado en recursos");
            System.out.println("   Caracteres: " + xmlContent.length());
            
            // 2. Convertir XML a JSON
            System.out.println("\n2. Convirtiendo XML a JSON...");
            JSONObject jsonObject = XML.toJSONObject(xmlContent);
            JSONArray recordsArray = jsonObject.getJSONObject("dataset").getJSONArray("record");
            
            System.out.println("   Registros convertidos: " + recordsArray.length());
            
            // 3. Guardar JSON en archivo
            System.out.println("\n3. Guardando JSON...");
            new File("resultados").mkdirs();
            
            String outputPath = "resultados/dataset.json";
            try (FileWriter writer = new FileWriter(outputPath)) {
                JSONArray jsonArray = new JSONArray();
                
                for (int i = 0; i < recordsArray.length(); i++) {
                    JSONObject record = recordsArray.getJSONObject(i);
                    jsonArray.put(record);
                }
                
                writer.write(jsonArray.toString(2));
                System.out.println("   JSON guardado en: " + outputPath);
                System.out.println("   Ruta completa: " + new File(outputPath).getAbsolutePath());
            }
            
            // 4. Mostrar ejemplo
            System.out.println("\n4. Ejemplo de conversion:");
            
            if (recordsArray.length() > 0) {
                JSONObject primerRegistro = recordsArray.getJSONObject(0);
                System.out.println("   Primer registro:");
                System.out.println("   ID: " + primerRegistro.getInt("id"));
                System.out.println("   Nombre: " + primerRegistro.getString("nombre"));
                System.out.println("   Apellido: " + primerRegistro.getString("apellido"));
                System.out.println("   Email: " + primerRegistro.getString("email"));
            }
            
            System.out.println("\nFIN DEL PROCESO");
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}