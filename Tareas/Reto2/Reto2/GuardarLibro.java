package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;

public class GuardarLibro {
    public static void main(String[] args) {
        
        try {
            XStream xstream = new XStream(new DomDriver());
            
            // Crear objeto Libro
            Libro libro = new Libro("El Quijote", "Miguel de Cervantes", 1605, 25.50);
            
            // Convertir a XML
            String xml = xstream.toXML(libro);
            System.out.println(" XML generado:");
            System.out.println(xml);
            
            // Guardar en archivo
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents" + 
                                  File.separator + "ficheros_java" + File.separator;
            new File(rutaDocumentos).mkdirs();
            
            String rutaArchivo = rutaDocumentos + "libro.xml";
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                writer.write(xml);
                System.out.println("\n Archivo guardado en: " + rutaArchivo);
            }
            
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}