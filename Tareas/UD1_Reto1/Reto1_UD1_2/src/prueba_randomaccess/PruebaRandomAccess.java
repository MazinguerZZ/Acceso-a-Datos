package prueba_randomaccess;

import java.io.*;
import java.util.*;

public class PruebaRandomAccess {
    
    private static final String ARCHIVO = "datos.bin";
    private static final int TAMANIO_ARRAY = 20;
    private static final int BYTES_POR_INT = 4;
    
    public static void main(String[] args) {
        int[] numeros = new int[TAMANIO_ARRAY];
        
        File archivo = new File(ARCHIVO);
        
        // 1. Inicializar o cargar datos
        if (archivo.exists()) {
            System.out.println("Cargando datos existentes...");
            cargarDatos(numeros);
        } else {
            System.out.println("Creando nuevo archivo con ceros...");
            inicializarArchivo();
            Arrays.fill(numeros, 0);
        }
        
        // 2. Mostrar contenido inicial
        System.out.println("\nContenido inicial:");
        mostrarArray(numeros);
        
        // 3. Bucle de modificación
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "rwd")) {
            Scanner scanner = new Scanner(System.in);
            int posicion;
            
            do {
                System.out.print("\nPosición a modificar (0-19, -1 para salir): ");
                posicion = scanner.nextInt();
                
                if (posicion >= 0 && posicion < TAMANIO_ARRAY) {
                    System.out.print("Nuevo valor: ");
                    int nuevoValor = scanner.nextInt();
                    
                    // Actualizar array
                    numeros[posicion] = nuevoValor;
                    
                    // Actualizar archivo
                    actualizarPosicion(raf, posicion, nuevoValor);
                    
                    System.out.println("Valor actualizado.");
                    mostrarArray(numeros);
                    
                } else if (posicion != -1) {
                    System.out.println("Posición inválida.");
                }
                
            } while (posicion != -1);
            
            scanner.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void cargarDatos(int[] numeros) {
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(ARCHIVO))) {
            
            for (int i = 0; i < TAMANIO_ARRAY; i++) {
                numeros[i] = dis.readInt();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            Arrays.fill(numeros, 0);
        }
    }
    
    private static void inicializarArchivo() {
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(ARCHIVO))) {
            
            for (int i = 0; i < TAMANIO_ARRAY; i++) {
                dos.writeInt(0);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void actualizarPosicion(RandomAccessFile raf, int posicion, int valor) 
            throws IOException {
        // Calcular offset (cada int ocupa 4 bytes)
        long offset = posicion * BYTES_POR_INT;
        raf.seek(offset);
        raf.writeInt(valor);
    }
    
    private static void mostrarArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%3d: %5d", i, array[i]);
            if ((i + 1) % 5 == 0) {
                System.out.println();
            } else {
                System.out.print(" | ");
            }
        }
    }
}
