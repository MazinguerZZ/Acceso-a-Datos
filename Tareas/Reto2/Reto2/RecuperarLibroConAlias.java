package Reto2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;

public class RecuperarLibroConAlias {
    public static void main(String[] args) {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);


            
            // Configurar los MISMOS aliases que en GuardarLibroConAlias
            xstream.alias("Libro", Libro.class);
            xstream.aliasField("Titulo", Libro.class, "titulo");
            xstream.aliasField("Autor", Libro.class, "autor");
            xstream.aliasField("AñoPublicacion", Libro.class, "año");
            xstream.aliasField("Precio", Libro.class, "precio");
            
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents" + 
                                  File.separator + "ficheros_java" + File.separator;
            File archivo = new File(rutaDocumentos + "libro_alias.xml");
            
            if (!archivo.exists()) {
                System.out.println("El archivo libro_alias.xml no existe. Ejecuta primero GuardarLibroConAlias.");
                return;
            }
            
            try (FileReader reader = new FileReader(archivo)) {
                Libro libroRecuperado = (Libro) xstream.fromXML(reader);
                
                System.out.println("Libro recuperado con aliases:");
                System.out.println("   Título: " + libroRecuperado.titulo);
                System.out.println("   Autor: " + libroRecuperado.autor);
                System.out.println("   Año: " + libroRecuperado.año);
                System.out.println("   Precio: " + libroRecuperado.precio + "€");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}