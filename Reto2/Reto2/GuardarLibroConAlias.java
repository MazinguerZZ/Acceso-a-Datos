package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;

public class GuardarLibroConAlias {
    public static void main(String[] args) {
        
        try {
            XStream xstream = new XStream(new DomDriver());
            
            // Configurar aliases para personalizar el XML
            xstream.alias("Libro", Libro.class);
            xstream.aliasField("Titulo", Libro.class, "titulo");
            xstream.aliasField("Autor", Libro.class, "autor");
            xstream.aliasField("AñoPublicacion", Libro.class, "año");
            xstream.aliasField("Precio", Libro.class, "precio");
            
            // Crear libro
            Libro libro = new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 20.00);
            
            // Convertir a XML
            String xml = xstream.toXML(libro);
            System.out.println("XML con aliases:");
            System.out.println(xml);
            
            // Guardar en archivo
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents" + 
                                  File.separator + "ficheros_java" + File.separator;
            new File(rutaDocumentos).mkdirs();
            
            String rutaArchivo = rutaDocumentos + "libro_alias.xml";
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                writer.write(xml);
                System.out.println("\nArchivo guardado en: " + rutaArchivo);
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
