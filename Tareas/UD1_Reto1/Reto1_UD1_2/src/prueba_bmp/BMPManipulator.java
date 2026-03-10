package prueba_bmp;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BMPManipulator {
    
    public static void main(String[] args) {
        String archivoBMP = "C:\\Users\\Tellus\\Desktop\\Instituto\\DAM-2\\Acceso a Datos\\Reto1_UD1_2\\prueba2.bmp";
        
        try (RandomAccessFile raf = new RandomAccessFile(archivoBMP, "r")) {
            
            // Leer cabecera BMP
            byte[] header = new byte[54];
            raf.read(header);
            
            // Verificar firma BMP
            if (header[0] != 'B' || header[1] != 'M') {
                System.out.println("No es un archivo BMP válido");
                return;
            }
            
            // Obtener ancho (offset 18, 4 bytes, little-endian)
            int ancho = bytesToIntLittleEndian(header, 18);
            
            // Obtener alto (offset 22, 4 bytes, little-endian)
            int alto = bytesToIntLittleEndian(header, 22);
            
            System.out.println("Dimensiones originales: " + ancho + "x" + alto);
            
            // Modificar dimensiones
            try (RandomAccessFile rafMod = new RandomAccessFile("imagen_modificada.bmp", "rw")) {
                raf.seek(0);
                
                // Copiar archivo original
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = raf.read(buffer)) != -1) {
                    rafMod.write(buffer, 0, bytesRead);
                }
                
                // Modificar ancho a 800
                rafMod.seek(18);
                rafMod.writeInt(Integer.reverseBytes(800));
                
                // Modificar alto a 600
                rafMod.seek(22);
                rafMod.writeInt(Integer.reverseBytes(600));
                
                System.out.println("Dimensiones modificadas a: 800x600");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static int bytesToIntLittleEndian(byte[] bytes, int offset) {
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, 4);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getInt();
    }
}
