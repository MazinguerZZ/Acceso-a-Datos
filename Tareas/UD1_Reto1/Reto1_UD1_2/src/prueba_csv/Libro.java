package prueba_csv;

import java.io.*;
import java.util.*;

class Libro {
    private String titulo;
    private String autor;
    private int anio;
    private double precio;
    
    public Libro(String titulo, String autor, int anio, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
    }
    
    
    
    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", titulo, autor, anio, precio);
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

	public static Libro fromCSV(String csvLine) {
        String[] partes = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        // Este regex maneja comas dentro de comillas
        
        // Limpiar comillas si existen
        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
            if (partes[i].startsWith("\"") && partes[i].endsWith("\"")) {
                partes[i] = partes[i].substring(1, partes[i].length() - 1);
            }
        }
        
        return new Libro(
            partes[0],
            partes[1],
            Integer.parseInt(partes[2]),
            Double.parseDouble(partes[3])
        );
    }
}
