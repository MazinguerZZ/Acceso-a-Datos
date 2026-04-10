package AccessoDatos;

import java.io.*;
import java.util.Properties;

public class ConfiguracionApp {
    public static void main(String[] args) {
        // Ruta a la carpeta Documentos
        String rutaDocumentos = System.getProperty("user.home") + "\\Documents\\ficheros_java\\";
        
        // Crear la carpeta si no existe
        new File(rutaDocumentos).mkdirs();
        
        Properties config = new Properties();
        config.setProperty("usuario", "ana");
        config.setProperty("password", "1234");
        config.setProperty("ultima_conexion", "2024-09-23");
        
        try {
            // Guardar en Documentos
            config.store(new FileOutputStream(rutaDocumentos + "config.txt"), "Mi configuración");
            config.storeToXML(new FileOutputStream(rutaDocumentos + "config.xml"), "Config XML");
            
            System.out.println("Configuraciones guardadas en: " + rutaDocumentos);
            
            // Leer lo guardado
            Properties configLeida = new Properties();
            configLeida.load(new FileInputStream(rutaDocumentos + "config.txt"));
            
            System.out.println("Usuario: " + configLeida.getProperty("usuario"));
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}