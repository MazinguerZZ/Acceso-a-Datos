package org.dam2.instituto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "PROFESOR")  // Tabla que almacena los profesores
public class Profesor {
    
    @Id
    @Column(name = "DNI")
    private String dni;  // DNI como clave primaria del profesor
    
    @Column(name = "ESPECIALIDAD")
    private String especialidad;  // Área de especialización del profesor
    
    @Column(name = "NOMBRE")
    private String nombre;  // Nombre del profesor
    
    // Relación One-to-Many: Un profesor puede impartir varios módulos
    // mappedBy indica que la relación está mapeada por el atributo "profesor" en Modulo
    @OneToMany(mappedBy = "profesor")
    private Set<Modulo> modulos = new HashSet<>();  // Módulos que imparte el profesor
    
    // Relación One-to-One (lado inverso): Un profesor puede ser tutor de un grupo
    // mappedBy indica que esta relación es el lado inverso de la definida en Grupo
    @OneToOne(mappedBy = "tutor")
    private Grupo grupo;  // Grupo del que es tutor (si lo es)

    public Profesor() {
    }

    // Getters y setters para acceder y modificar los atributos privados
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(Set<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}