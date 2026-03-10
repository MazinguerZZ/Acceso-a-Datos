package Json;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class JsonHierarchicalSimple {
    public static void main(String[] args) {
        System.out.println("Ejemplo JsonHierarchicalStreamDriver");
        
        XStream xs = new XStream(new JsonHierarchicalStreamDriver());
        xs.alias("libro", LibroSimple.class);
        
        LibroSimple libro = new LibroSimple("Harry Potter", "J.K. Rowling", 1997, 19.99);
        
        String json = xs.toXML(libro);
        System.out.println(json);
    }
    
    static class LibroSimple {
        String titulo;
        String autor;
        int año;
        double precio;
        
        public LibroSimple(String t, String a, int año, double p) {
            titulo = t; autor = a; this.año = año; precio = p;
        }
    }
}