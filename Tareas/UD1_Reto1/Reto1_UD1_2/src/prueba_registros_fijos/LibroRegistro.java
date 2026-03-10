package prueba_registros_fijos;

import java.io.*;
import java.nio.charset.StandardCharsets;

class LibroRegistro {
    private String titulo;    // 100 bytes
    private String autor;     // 50 bytes
    private int anio;         // 4 bytes
    private double precio;    // 8 bytes
    private boolean activo;   // 1 byte
    
    public static final int TAMANIO_REGISTRO = 100 + 50 + 4 + 8 + 1;
    
    public LibroRegistro(String titulo, String autor, int anio, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
        this.activo = true;
    }
    
    // Método para escribir registro en RandomAccessFile
    public void escribirEnArchivo(RandomAccessFile raf, long posicion) throws IOException {
        raf.seek(posicion);
        
        // Escribir título (100 bytes)
        byte[] tituloBytes = titulo.getBytes(StandardCharsets.UTF_8);
        raf.write(tituloBytes, 0, Math.min(tituloBytes.length, 100));
        if (tituloBytes.length < 100) {
            raf.write(new byte[100 - tituloBytes.length]);
        }
        
        // Escribir autor (50 bytes)
        byte[] autorBytes = autor.getBytes(StandardCharsets.UTF_8);
        raf.write(autorBytes, 0, Math.min(autorBytes.length, 50));
        if (autorBytes.length < 50) {
            raf.write(new byte[50 - autorBytes.length]);
        }
        
        // Escribir año
        raf.writeInt(anio);
        
        // Escribir precio
        raf.writeDouble(precio);
        
        // Escribir estado activo
        raf.writeBoolean(activo);
    }
    
    // Método para leer registro desde RandomAccessFile
    public static LibroRegistro leerDesdeArchivo(RandomAccessFile raf, long posicion) 
            throws IOException {
        raf.seek(posicion);
        
        // Leer título
        byte[] tituloBytes = new byte[100];
        raf.readFully(tituloBytes);
        String titulo = new String(tituloBytes, StandardCharsets.UTF_8).trim();
        
        // Leer autor
        byte[] autorBytes = new byte[50];
        raf.readFully(autorBytes);
        String autor = new String(autorBytes, StandardCharsets.UTF_8).trim();
        
        // Leer año
        int anio = raf.readInt();
        
        // Leer precio
        double precio = raf.readDouble();
        
        // Leer estado activo
        boolean activo = raf.readBoolean();
        
        LibroRegistro libro = new LibroRegistro(titulo, autor, anio, precio);
        libro.setActivo(activo);
        
        return libro;
    }
    
    
    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public static int getTamanioRegistro() {
		return TAMANIO_REGISTRO;
	}

	@Override
    public String toString() {
        return String.format("%-30s %-20s %6d %8.2f %s", 
            titulo.length() > 30 ? titulo.substring(0, 27) + "..." : titulo,
            autor.length() > 20 ? autor.substring(0, 17) + "..." : autor,
            anio, precio, activo ? "A" : "I");
    }
}

