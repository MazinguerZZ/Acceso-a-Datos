package prueba_properties;

import java.io.*;
import java.util.Properties;

public class PruebaProperties {
    public static void main(String[] args) {
        Properties propiedades = new Properties();
        
        // 1. Añadir pares clave/valor
        propiedades.setProperty("titulo", "Cien años de soledad");
        propiedades.setProperty("autor", "Gabriel García Márquez");
        propiedades.setProperty("anio", "1967");
        propiedades.setProperty("paginas", "471");
        propiedades.setProperty("idioma", "español");
        
        try {
            // 2. Guardar en fichero .properties
            try (FileOutputStream fos = new FileOutputStream("libros.properties")) {
                propiedades.store(fos, "Propiedades de libros - No modificar manualmente");
            }
            
            // 3. Guardar en fichero XML
            try (FileOutputStream fosXML = new FileOutputStream("libros.xml")) {
                propiedades.storeToXML(fosXML, "Propiedades de libros en XML");
            }
            
            System.out.println("Ficheros creados correctamente.");
            
            // 4. Recuperar datos desde .properties
            Properties propiedadesCargadas = new Properties();
            try (FileInputStream fis = new FileInputStream("libros.properties")) {
                propiedadesCargadas.load(fis);
            }
            
            System.out.println("\nDatos cargados desde .properties:");
            propiedadesCargadas.forEach((k, v) -> 
                System.out.println(k + ": " + v));
            
            // 5. Recuperar datos desde XML
            Properties propiedadesXML = new Properties();
            try (FileInputStream fisXML = new FileInputStream("libros.xml")) {
                propiedadesXML.loadFromXML(fisXML);
            }
            
            System.out.println("\nDatos cargados desde XML:");
            propiedadesXML.forEach((k, v) -> 
                System.out.println(k + ": " + v));
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
