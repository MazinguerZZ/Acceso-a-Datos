package JPA_Ejer2_3_4;

import jakarta.persistence.*;

@Entity
@Table(name = "PROYECTO")
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROYECTO_ID")
    private Long id;
    
    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPNO", foreignKey = @ForeignKey(name = "FK_PROYECTO_EMP"))
    private Scott2 empleado;
    
    public Proyecto() {}
    
    public Proyecto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Scott2 getEmpleado() { return empleado; }
    public void setEmpleado(Scott2 empleado) { this.empleado = empleado; }
    
    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", nombre=" + nombre + 
               ", descripcion=" + descripcion + "]";
    }
}