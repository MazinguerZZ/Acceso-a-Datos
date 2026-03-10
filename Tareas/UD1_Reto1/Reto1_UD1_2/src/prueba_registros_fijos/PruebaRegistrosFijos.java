package prueba_registros_fijos;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class PruebaRegistrosFijos {
    
    public static void main(String[] args) {
        String archivo = "libros_registros.dat";
        
        List<LibroRegistro> libros = Arrays.asList(
            new LibroRegistro("Cien años de soledad", "Gabriel García Márquez", 1967, 19.99),
            new LibroRegistro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 25.50),
            new LibroRegistro("Rayuela", "Julio Cortázar", 1963, 22.00)
        );
        
        // 1. Guardar registros
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            for (int i = 0; i < libros.size(); i++) {
                long posicion = i * LibroRegistro.TAMANIO_REGISTRO;
                libros.get(i).escribirEnArchivo(raf, posicion);
            }
            System.out.println("Registros guardados.");
            
            // 2. Leer registro específico (el segundo)
            System.out.println("\nLeyendo registro en posición 1:");
            LibroRegistro libroLeido = LibroRegistro.leerDesdeArchivo(raf, 
                1 * LibroRegistro.TAMANIO_REGISTRO);
            System.out.println(libroLeido);
            
            // 3. Modificar registro específico
            System.out.println("\nModificando precio del primer registro...");
            LibroRegistro libroModificado = LibroRegistro.leerDesdeArchivo(raf, 0);
            libroModificado.setPrecio(21.99);
            libroModificado.escribirEnArchivo(raf, 0);
            
            // 4. Mostrar todos los registros
            System.out.println("\nTodos los registros:");
            raf.seek(0);
            for (int i = 0; i < libros.size(); i++) {
                LibroRegistro libro = LibroRegistro.leerDesdeArchivo(raf, 
                    i * LibroRegistro.TAMANIO_REGISTRO);
                System.out.println(libro);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Verificar tamaño del archivo
        File f = new File(archivo);
        System.out.printf("\nTamaño del archivo: %d bytes\n", f.length());
        System.out.printf("Tamaño calculado: %d registros × %d bytes = %d bytes\n", 
            libros.size(), LibroRegistro.TAMANIO_REGISTRO, 
            libros.size() * LibroRegistro.TAMANIO_REGISTRO);
    }
}