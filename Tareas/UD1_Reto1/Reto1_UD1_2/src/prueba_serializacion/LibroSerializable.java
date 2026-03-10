package prueba_serializacion;

import java.io.*;
import java.util.*;

class LibroSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;
    private int anio;
    private transient double precio; // No se serializa
    
    public LibroSerializable(String titulo, String autor, int anio, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
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




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
    public String toString() {
        return String.format("%s - %s (%d) - %.2f€", titulo, autor, anio, precio);
    }
}
