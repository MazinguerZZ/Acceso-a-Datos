package Reto2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class EscribirLibrosDOM {
    public static void main(String[] args) {
    	
        try {
            // Crear libros como los que usas en XStream
            Libro libro1 = new Libro("El Quijote", "Miguel de Cervantes", 1605, 25.50);
            Libro libro2 = new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 20.00);
            Libro libro3 = new Libro("1984", "George Orwell", 1949, 15.75);
            
            // Crear documento XML con DOM (como en el PDF)
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Elemento raíz
            Element biblioteca = doc.createElement("biblioteca");
            doc.appendChild(biblioteca);

            // PRIMER LIBRO
            Element libroElement1 = doc.createElement("libro");
            biblioteca.appendChild(libroElement1);

            // Atributo para el libro
            Attr attr1 = doc.createAttribute("id");
            attr1.setValue("1");
            libroElement1.setAttributeNode(attr1);

            // Elementos del libro
            Element titulo1 = doc.createElement("titulo");
            titulo1.appendChild(doc.createTextNode(libro1.titulo));
            libroElement1.appendChild(titulo1);

            Element autor1 = doc.createElement("autor");
            autor1.appendChild(doc.createTextNode(libro1.autor));
            libroElement1.appendChild(autor1);

            Element año1 = doc.createElement("año");
            año1.appendChild(doc.createTextNode(String.valueOf(libro1.año)));
            libroElement1.appendChild(año1);

            Element precio1 = doc.createElement("precio");
            precio1.appendChild(doc.createTextNode(String.valueOf(libro1.precio)));
            libroElement1.appendChild(precio1);

            // SEGUNDO LIBRO
            Element libroElement2 = doc.createElement("libro");
            biblioteca.appendChild(libroElement2);

            Attr attr2 = doc.createAttribute("id");
            attr2.setValue("2");
            libroElement2.setAttributeNode(attr2);

            Element titulo2 = doc.createElement("titulo");
            titulo2.appendChild(doc.createTextNode(libro2.titulo));
            libroElement2.appendChild(titulo2);

            Element autor2 = doc.createElement("autor");
            autor2.appendChild(doc.createTextNode(libro2.autor));
            libroElement2.appendChild(autor2);

            Element año2 = doc.createElement("año");
            año2.appendChild(doc.createTextNode(String.valueOf(libro2.año)));
            libroElement2.appendChild(año2);

            Element precio2 = doc.createElement("precio");
            precio2.appendChild(doc.createTextNode(String.valueOf(libro2.precio)));
            libroElement2.appendChild(precio2);

            // TERCER LIBRO
            Element libroElement3 = doc.createElement("libro");
            biblioteca.appendChild(libroElement3);

            Attr attr3 = doc.createAttribute("id");
            attr3.setValue("3");
            libroElement3.setAttributeNode(attr3);

            Element titulo3 = doc.createElement("titulo");
            titulo3.appendChild(doc.createTextNode(libro3.titulo));
            libroElement3.appendChild(titulo3);

            Element autor3 = doc.createElement("autor");
            autor3.appendChild(doc.createTextNode(libro3.autor));
            libroElement3.appendChild(autor3);

            Element año3 = doc.createElement("año");
            año3.appendChild(doc.createTextNode(String.valueOf(libro3.año)));
            libroElement3.appendChild(año3);

            Element precio3 = doc.createElement("precio");
            precio3.appendChild(doc.createTextNode(String.valueOf(libro3.precio)));
            libroElement3.appendChild(precio3);

            // Guardar archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            // Formatear el XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            DOMSource source = new DOMSource(doc);
            
            String ruta = System.getProperty("user.home") + "/Documents/ficheros_java";
            new File(ruta).mkdirs();
            StreamResult result = new StreamResult(new File(ruta + "biblioteca_dom.xml"));

            transformer.transform(source, result);
            
            System.out.println(" Archivo creado: biblioteca_dom.xml");
            System.out.println("Con 3 libros creados con DOM");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}