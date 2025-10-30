package AccessoDatos;

import java.io.*;
import java.util.*;

class Libro {
    public String titulo;
    public String autor;
    public int año;
    
    public Libro(String titulo, String autor, int año) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
    }
    
    public String toCSV() {
        return titulo + "," + autor + "," + año;
    }
}

public class GuardarLibrosCSV {
    public static void main(String[] args) {
        // Ruta a Documentos
        String rutaDocumentos = System.getProperty("user.home") + "\\Documents\\ficheros_java\\";
        new File(rutaDocumentos).mkdirs();
        
        List<Libro> biblioteca = new ArrayList<>();
        biblioteca.add(new Libro("El Quijote", "Cervantes", 1605));
        biblioteca.add(new Libro("Cien años de soledad", "García Márquez", 1967));
        
        try {
            // Guardar en Documentos
            PrintWriter writer = new PrintWriter(rutaDocumentos + "biblioteca.csv");
            writer.println("Título,Autor,Año");
            
            for (Libro libro : biblioteca) {
                writer.println(libro.toCSV());
            }
            writer.close();
            
            System.out.println("CSV guardado en: " + rutaDocumentos);
            
            // Leer el CSV
            BufferedReader reader = new BufferedReader(new FileReader(rutaDocumentos + "biblioteca.csv"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}