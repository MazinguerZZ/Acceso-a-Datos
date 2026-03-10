package org.dam2.instituto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity  // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos
@Table(name = "ALUMNO")  // Especifica el nombre de la tabla en la base de datos
public class Alumno {
    
    @Id  // Marca este campo como la clave primaria de la entidad
    @Column(name = "NIA")  // Mapea este campo a la columna "NIA" en la tabla
    private String nia;  // NIA: Número de Identificación del Alumno (clave primaria)
    
    @Column(name = "FECHANACIMIENTO")
    private String fecha;  // Fecha de nacimiento del alumno
    
    @Column(name = "NOMBRE")
    private String nombre;  // Nombre del alumno
    
    // Relación Many-to-One: Muchos alumnos pueden pertenecer a un grupo
    @ManyToOne
    @JoinColumn(name = "GRUPO_NOMBRE")  // Clave foránea que referencia a la tabla GRUPO
    private Grupo grupo;  // Grupo al que pertenece el alumno
    
    // Relación Many-to-Many: Un alumno puede estar matriculado en muchos módulos
    // y un módulo puede tener muchos alumnos matriculados
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "ALUMNO_MODULO",  // Nombre de la tabla intermedia que gestiona la relación N-N
        joinColumns = @JoinColumn(name = "alumnos_NIA"),  // Clave foránea hacia ALUMNO
        inverseJoinColumns = @JoinColumn(name = "modulos_ID")  // Clave foránea hacia MODULO
    )
    private Set<Modulo> modulos = new HashSet<>();  // Conjunto de módulos en los que está matriculado

    // Constructor vacío requerido por JPA
    public Alumno() {
    }

    // Getters y setters para acceder y modificar los atributos privados
    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Set<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(Set<Modulo> modulos) {
        this.modulos = modulos;
    }
}