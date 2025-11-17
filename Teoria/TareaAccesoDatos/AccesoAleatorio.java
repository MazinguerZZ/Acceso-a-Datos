package AccessoDatos;

import java.io.*;

public class AccesoAleatorio {
    public static void main(String[] args) {
        // Ruta a Documentos
        String rutaDocumentos = System.getProperty("user.home") + "\\Documents\\ficheros_java\\";
        new File(rutaDocumentos).mkdirs();
        
        try {
            RandomAccessFile archivo = new RandomAccessFile(rutaDocumentos + "datos.dat", "rw");
            
            // Escribir datos
            archivo.writeUTF("Ana");
            archivo.writeInt(25);
            archivo.writeDouble(1500.50);
            
            System.out.println("Datos guardados en: " + rutaDocumentos);
            
            // Leer datos específicos
            archivo.seek(archivo.length() - 8);
            double salario = archivo.readDouble();
            System.out.println("Salario: " + salario);
            
            // Volver al principio
            archivo.seek(0);
            System.out.println("Nombre: " + archivo.readUTF());
            System.out.println("Edad: " + archivo.readInt());
            
            archivo.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}