package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;

public class RecuperarLibro {
    public static void main(String[] args) {
        
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents" + 
                                  File.separator + "ficheros_java" + File.separator;
            File archivo = new File(rutaDocumentos + "libro.xml");
            
            if (!archivo.exists()) {
                System.out.println(" El archivo no existe. Ejecuta primero GuardarLibro.");
                return;
            }
            
            // Leer XML y convertir a objeto
            try (FileReader reader = new FileReader(archivo)) {
                Libro libroRecuperado = (Libro) xstream.fromXML(reader);
                
                System.out.println(" Libro recuperado correctamente:");
                System.out.println("   Título: " + libroRecuperado.titulo);
                System.out.println("   Autor: " + libroRecuperado.autor);
                System.out.println("   Año: " + libroRecuperado.año);
                System.out.println("   Precio: " + libroRecuperado.precio + "€");
            }
            
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}