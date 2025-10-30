package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;

public class ProbarXStreamLeeDOM {
    public static void main(String[] args) {
        
        try {
            String ruta = System.getProperty("user.home") + "/Documents/ficheros_java";
            File archivoDOM = new File(ruta + "biblioteca_dom.xml");
            File archivoXStream = new File(ruta + "libro.xml");
            
            if (!archivoDOM.exists()) {
                System.out.println("Primero ejecuta EscribirLibrosDOM");
                return;
            }
            
            XStream xstream = new XStream(new DomDriver());
            
            System.out.println("1. Probando leer XML de DOM con XStream...");
            try (FileReader reader = new FileReader(archivoDOM)) {
                Object objeto = xstream.fromXML(reader);
                System.out.println("    NO debería funcionar: " + objeto);
            } catch (Exception e) {
                System.out.println("    Correcto - Falló: " + e.getMessage());
            }
            
            System.out.println("\n2. Probando leer XML de XStream con XStream...");
            if (archivoXStream.exists()) {
                try (FileReader reader = new FileReader(archivoXStream)) {
                    Libro libro = (Libro) xstream.fromXML(reader);
                    System.out.println("    Funcionó: " + libro.titulo);
                } catch (Exception e) {
                    System.out.println("   Error: " + e.getMessage());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
