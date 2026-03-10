package JPA_Ejer2_3_4;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "HABILIDAD")
public class Habilidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HABILIDAD_ID")
    private Long id;
    
    @Column(name = "NOMBRE", length = 30)
    private String nombre;
    
    @Column(name = "NIVEL", length = 20)
    private String nivel;
    
    @ManyToMany(mappedBy = "habilidades")
    private List<Scott2> empleados = new ArrayList<>();
    
    public Habilidad() {}
    
    public Habilidad(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    
    public List<Scott2> getEmpleados() { return empleados; }
    public void setEmpleados(List<Scott2> empleados) { this.empleados = empleados; }
    
    @Override
    public String toString() {
        return nombre + " (" + nivel + ")";
    }
}