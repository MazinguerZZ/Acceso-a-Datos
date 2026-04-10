package AccessoDatos;

import java.io.*;
import java.util.*;

class librito implements Serializable {
    private static final long serialVersionUID = 1L;
    public String titulo;
    public String autor;
    public int año;
    public double precio;
    
    public librito(String titulo, String autor, int año, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.precio = precio;
    }
}

public class Serializacion {
    public static void main(String[] args) {
        
        // Obtener la ruta de la carpeta de documentos
        String rutaDocumentos = System.getProperty("user.home") + "\\Documents\\ficheros_java\\";
        new File(rutaDocumentos).mkdirs();
        
        
        // Crear la ruta completa del archivo
        File archivoBiblioteca = new File(rutaDocumentos, "biblioteca.dat");
        
        // Crear algunos libros
        ArrayList<librito> misLibros = new ArrayList<>();
        misLibros.add(new librito("El Quijote", "Cervantes", 1605, 25.50));
        misLibros.add(new librito("Cien años de soledad", "García Márquez", 1967, 20.00));
        
        try {
            System.out.println("Voy a guardar los libros en: " + archivoBiblioteca.getAbsolutePath());
            
            // Guardar los libros en un archivo
            FileOutputStream archivoSalida = new FileOutputStream(archivoBiblioteca);
            ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);
            salida.writeObject(misLibros);
            salida.close();
            
            System.out.println("Libros guardados en: " + archivoBiblioteca.getAbsolutePath());
            
            System.out.println("\nAhora voy a cargar los libros...");
            
            // Cargar los libros desde el archivo
            FileInputStream archivoEntrada = new FileInputStream(archivoBiblioteca);
            ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);
            
            // Leer el objeto (necesita un cast)
            Object objetoLeido = entrada.readObject();
            entrada.close();
            
            // Convertirlo a ArrayList de Libros
            ArrayList<librito> librosCargados = (ArrayList<librito>) objetoLeido;
            
            System.out.println("Libros cargados correctamente:");
            
            // Mostrar los libros
            for (librito libro : librosCargados) {
                System.out.println("- " + libro.titulo + " de " + libro.autor);
                System.out.println("  Año: " + libro.año + " | Precio: " + libro.precio);
            }
            
        } catch (Exception e) {
            System.out.println("Algo salió mal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}