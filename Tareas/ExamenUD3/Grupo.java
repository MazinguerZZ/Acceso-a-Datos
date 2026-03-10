package org.dam2.instituto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "GRUPO")  // Tabla que almacena los grupos/clases del instituto
public class Grupo {
    
    @Id
    @Column(name = "NOMBRE")
    private String nombre;  // El nombre del grupo actúa como clave primaria (ej: "1ºA", "2ºB")
    
    @Column(name = "UBICACION")
    private String ubicacion;  // Aula o ubicación donde se imparten las clases del grupo
    
    // Relación One-to-One: Cada grupo tiene un único tutor (profesor)
    @OneToOne
    @JoinColumn(name = "TUTOR_DNI")  // Clave foránea que referencia al profesor tutor
    private Profesor tutor;  // Profesor que tutela el grupo
    
    // Relación One-to-Many: Un grupo tiene muchos alumnos
    // mappedBy indica que la relación está mapeada por el atributo "grupo" en Alumno
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Alumno> alumnos = new HashSet<>();  // Conjunto de alumnos del grupo

    public Grupo() {
    }

    // Getters y setters para acceder y modificar los atributos privados
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Profesor getTutor() {
        return tutor;
    }

    public void setTutor(Profesor tutor) {
        this.tutor = tutor;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}