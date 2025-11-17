package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.*;

public class PruebaSerializacionLista {
    public static void main(String[] args) {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);

            
            // Crear lista de libros
            List<Libro> biblioteca = new ArrayList<>();
            biblioteca.add(new Libro("El Quijote", "Miguel de Cervantes", 1605, 25.50));
            biblioteca.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 20.00));
            biblioteca.add(new Libro("1984", "George Orwell", 1949, 15.75));
            
            // Configurar alias para la lista
            xstream.alias("Biblioteca", List.class);
            xstream.alias("Libro", Libro.class);
            
            // Serializar lista
            String xml = xstream.toXML(biblioteca);
            System.out.println("XML de la biblioteca:");
            System.out.println(xml);
            
            // Guardar en archivo
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents" + 
                                  File.separator + "ficheros_java" + File.separator;
            new File(rutaDocumentos).mkdirs();
            
            String rutaArchivo = rutaDocumentos + "biblioteca_lista.xml";
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                writer.write(xml);
                System.out.println("\nArchivo guardado en: " + rutaArchivo);
            }
            
            // Recuperar lista desde archivo
            System.out.println("\n--- RECUPERANDO LISTA ---");
            try (FileReader reader = new FileReader(rutaArchivo)) {
                @SuppressWarnings("unchecked")
				List<Libro> bibliotecaRecuperada = (List<Libro>) xstream.fromXML(reader);
                
                System.out.println("Biblioteca recuperada (" + bibliotecaRecuperada.size() + " libros):");
                for (Libro libro : bibliotecaRecuperada) {
                    System.out.println(" - " + libro);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
