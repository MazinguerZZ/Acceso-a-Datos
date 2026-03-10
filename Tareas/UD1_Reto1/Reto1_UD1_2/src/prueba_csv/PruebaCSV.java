package prueba_csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebaCSV {
    
    public static void guardarCSV(List<Libro> libros, String archivo, String charset) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(archivo), charset))) {
            
            // Escribir cabecera
            writer.println("Título,Autor,Año,Precio");
            
            // Escribir cada libro
            for (Libro libro : libros) {
                // Escapar comas en el título o autor
                String titulo = libro.getTitulo().contains(",") ? 
                    "\"" + libro.getTitulo() + "\"" : libro.getTitulo();
                String autor = libro.getAutor().contains(",") ?
                    "\"" + libro.getAutor() + "\"" : libro.getAutor();
                
                writer.printf("%s,%s,%d,%.2f%n", 
                    titulo, autor, libro.getAnio(), libro.getPrecio());
            }
            
            System.out.println("Archivo guardado con codificación: " + charset);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Libro> cargarCSV(String archivo, String charset) {
        List<Libro> libros = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), charset))) {
            
            String linea;
            boolean primera = true;
            
            while ((linea = reader.readLine()) != null) {
                if (primera) {
                    primera = false; // Saltar cabecera
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    libros.add(Libro.fromCSV(linea));
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return libros;
    }
    
    public static void main(String[] args) {
        List<Libro> libros = Arrays.asList(
            new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 19.99),
            new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 25.50),
            new Libro("La ciudad y los perros", "Mario Vargas Llosa", 1963, 18.75),
            new Libro("Rayuela", "Julio Cortázar", 1963, 22.00)
        );
        
        // Prueba con diferentes codificaciones
        String[] codificaciones = {"UTF-8", "ISO-8859-1", "Windows-1252"};
        
        for (String charset : codificaciones) {
            String archivo = "libros_" + charset + ".csv";
            guardarCSV(libros, archivo, charset);
            
            List<Libro> cargados = cargarCSV(archivo, charset);
            System.out.println("Libros cargados con " + charset + ": " + cargados.size());
            System.out.println("¿Maneja acentos y eñes? " + 
                (cargados.get(0).getAutor().contains("á") ? "Sí" : "No"));
        }
        
        // Prueba con datos problemáticos
        List<Libro> librosProblema = Arrays.asList(
            new Libro("El nombre, con coma", "Autor Test", 2020, 15.99),
            new Libro("Libro \"con comillas\"", "Autor", 2021, 12.50)
        );
        
        guardarCSV(librosProblema, "libros_problema.csv", "UTF-8");
        List<Libro> recuperados = cargarCSV("libros_problema.csv", "UTF-8");
        
        System.out.println("\nDatos problemáticos recuperados correctamente: " + 
            (recuperados.size() == librosProblema.size()));
    }
}

