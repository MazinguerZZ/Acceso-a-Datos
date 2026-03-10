package prueba_serializacion;
import java.io.*;
import java.util.*;

public class PruebaSerializacion {
    
    public static void serializar(List<LibroSerializable> libros, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(archivo))) {
            
            oos.writeObject(libros);
            System.out.println("Objetos serializados correctamente");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<LibroSerializable> deserializar(String archivo) {
        List<LibroSerializable> libros = new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(archivo))) {
            
            libros = (List<LibroSerializable>) ois.readObject();
            System.out.println("Objetos deserializados correctamente");
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return libros;
    }
    
    public static void main(String[] args) {
        List<LibroSerializable> libros = Arrays.asList(
            new LibroSerializable("1984", "George Orwell", 1949, 15.99),
            new LibroSerializable("Fahrenheit 451", "Ray Bradbury", 1953, 14.50),
            new LibroSerializable("Un mundo feliz", "Aldous Huxley", 1932, 16.75)
        );
        
        // Serializar
        serializar(libros, "libros_serializados.dat");
        
        // Deserializar
        List<LibroSerializable> recuperados = deserializar("libros_serializados.dat");
        
        System.out.println("\nLibros recuperados:");
        recuperados.forEach(System.out::println);
        
        // Nota: 'precio' será 0.0 porque se marcó como 'transient'
    }
}
