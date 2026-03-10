package Json;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;

public class ConvertirJson_A_XML_Pojo {

    // POJO que representa cada objeto del JSON - Creamos los atributos segun la estructura de nuestro JSON
    static class Persona {
        int id;
        String first_name;
        String last_name;
        String email;
        String gender;
        String ip_address;
    }

    public static void main(String[] args) {
        try {
            // Leer el archivo JSON
            String jsonString = new String(Files.readAllBytes(Paths.get("campos.json"))); // Leemos todos los bytes que contiene el fichero
            Gson gson = new Gson(); // Instanciamos el objeto gson 
            Persona[] personas = gson.fromJson(jsonString, Persona[].class); // Convertir JSON a array de POJOs usando Gson

            for (Persona p : personas) {  // Recorrer cada POJO y convertirlo a XML
                // Convertir el POJO a JSONObject
                JSONObject jsonObj = new JSONObject(gson.toJson(p));
                
                // Convertir JSONObject a XML
                String xml = XML.toString(jsonObj, "persona"); // "persona" será la etiqueta raíz de cada objeto
                System.out.println(xml); // Imprimir XML en consola el contenido del XML
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
