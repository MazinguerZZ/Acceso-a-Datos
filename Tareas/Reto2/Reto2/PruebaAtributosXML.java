package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.*;

public class PruebaAtributosXML {
    public static void main(String[] args) {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            
            // Crear libro
            Libro libro = new Libro("Rayuela", "Julio Cortázar", 1963, 18.90);
            
            // Configurar para usar atributos en lugar de elementos
            xstream.useAttributeFor(Libro.class, "año");
            xstream.useAttributeFor(Libro.class, "precio");
            xstream.alias("Libro", Libro.class);
            
            // Serializar
            String xml = xstream.toXML(libro);
            System.out.println("XML con atributos:");
            System.out.println(xml);
            
            // Guardar
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents" + 
                                  File.separator + "ficheros_java" + File.separator;
            new File(rutaDocumentos).mkdirs();
            
            String rutaArchivo = rutaDocumentos + "libro_atributos.xml";
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                writer.write(xml);
                System.out.println("\nArchivo guardado en: " + rutaArchivo);
            }
            

            System.out.println("\n--- PRUEBA DE RECUPERACIÓN SIN ALIASES ---");
            XStream xstreamSinAlias = new XStream(new DomDriver());
            xstreamSinAlias.addPermission(AnyTypePermission.ANY); 

            try (FileReader reader = new FileReader(rutaArchivo)) {
                Libro libroRecuperado = (Libro) xstreamSinAlias.fromXML(reader);
                System.out.println("ERROR: No debería funcionar sin aliases");
            } catch (Exception e) {
                System.out.println("Correcto - Falló sin aliases: " + e.getMessage());
            }
            

            System.out.println("\n--- PRUEBA DE RECUPERACIÓN CON ALIASES ---");
            XStream xstreamConAlias = new XStream(new DomDriver());
            xstreamConAlias.addPermission(AnyTypePermission.ANY); 
            xstreamConAlias.useAttributeFor(Libro.class, "año");
            xstreamConAlias.useAttributeFor(Libro.class, "precio");
            xstreamConAlias.alias("Libro", Libro.class);
            
            try (FileReader reader = new FileReader(rutaArchivo)) {
                Libro libroRecuperado = (Libro) xstreamConAlias.fromXML(reader);
                System.out.println("Éxito - Libro recuperado: " + libroRecuperado.titulo);
                System.out.println("Autor: " + libroRecuperado.autor);
                System.out.println("Año: " + libroRecuperado.año);
                System.out.println("Precio: " + libroRecuperado.precio);
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}