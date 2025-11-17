package Reto2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class LeerLibrosDOM {
    public static void main(String[] args) {
        
        try {
            // Cargar el archivo
            String ruta = System.getProperty("user.home") + "/Documents/ficheros_java";
            File archivo = new File(ruta + "biblioteca_dom.xml");
            
            // Verificar si existe
            if (!archivo.exists()) {
                System.out.println("Primero ejecuta EscribirLibrosDOM para crear el archivo");
                return;
            }
            
            // Parsear el fichero como en el PDF
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            
            // Normalizar como en el PDF
            doc.getDocumentElement().normalize();
            
            // Mostrar elemento raíz
            System.out.println("Elemento Raiz: " + doc.getDocumentElement().getNodeName());
            
            // Obtener lista de libros
            NodeList listaDeLibros = doc.getElementsByTagName("libro");
            System.out.println("Número de libros: " + listaDeLibros.getLength());
            
            // Recorrer libros como en el PDF
            for(int i = 0; i < listaDeLibros.getLength(); i++) {
                Node nodoLibro = listaDeLibros.item(i);

                if(nodoLibro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoLibro = (Element) nodoLibro;
                    
                    System.out.println("\nLibro id: " + elementoLibro.getAttribute("id"));
                    System.out.println("Título: " + elementoLibro.getElementsByTagName("titulo").item(0).getTextContent());
                    System.out.println("Autor: " + elementoLibro.getElementsByTagName("autor").item(0).getTextContent());
                    System.out.println("Año: " + elementoLibro.getElementsByTagName("año").item(0).getTextContent());
                    System.out.println("Precio: " + elementoLibro.getElementsByTagName("precio").item(0).getTextContent() + "€");
                }
            }
            
            System.out.println("\n Terminado de leer con DOM");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
