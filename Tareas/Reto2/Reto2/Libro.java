package Reto2;

public class Libro {
    public String titulo;
    public String autor;
    public int año;
    public double precio;
    
    public Libro() {}
    
    public Libro(String titulo, String autor, int año, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.año = año;
        this.precio = precio;
    }
    
    public String toString() {
        return titulo + " - " + autor + " (" + año + ") - " + precio + "€";
    }
}