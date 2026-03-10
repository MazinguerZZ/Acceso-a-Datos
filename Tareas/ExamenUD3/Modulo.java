package org.dam2.instituto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "MODULO")  // Tabla que almacena los módulos/asignaturas
public class Modulo {
    
    @Id
    @Column(name = "ID")
    private String id;  // Identificador único del módulo (clave primaria)
    
    @Column(name = "NOMBRE")
    private String nombre;  // Nombre del módulo (ej: "Matemáticas", "Programación")
    
    // Relación Many-to-One: Muchos módulos pueden ser impartidos por un profesor
    @ManyToOne
    @JoinColumn(name = "PROFESOR_DNI")  // Clave foránea que referencia al profesor
    private Profesor profesor;  // Profesor que imparte este módulo
    
    // Relación Many-to-Many (lado inverso): 
    // mappedBy indica que esta relación es el lado inverso de la definida en Alumno
    @ManyToMany(mappedBy = "modulos")
    private Set<Alumno> alumnos = new HashSet<>();  // Alumnos matriculados en este módulo

    public Modulo() {
    }
    
    // Getters y setters para acceder y modificar los atributos privados
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}