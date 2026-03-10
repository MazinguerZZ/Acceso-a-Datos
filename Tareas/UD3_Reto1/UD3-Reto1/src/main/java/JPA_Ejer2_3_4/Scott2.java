package JPA_Ejer2_3_4;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "EMP2") // Tabla diferente para no conflictos
public class Scott2 {
    
    @Id
    @Column(name = "EMPNO")
    private Long empno;
    
    @Column(name = "ENAME", length = 10)
    private String ename;
    
    @Column(name = "JOB", length = 20) // Aumentado a 20 caracteres
    private String job;
    
    @Column(name = "MGR")
    private Integer mgr;
    
    @Column(name = "SAL")
    private Integer sal;
    
    @Column(name = "COMM")
    private Integer comm;
    
    @Column(name = "DEPTNO")
    private Integer deptno;
    
    // RELACIÓN OneToMany: Un empleado tiene varios proyectos
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proyecto> proyectos = new ArrayList<>();
    
    // RELACIÓN ManyToMany: Un empleado tiene varias habilidades
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "EMP2_HABILIDAD2",
        joinColumns = @JoinColumn(name = "EMPNO"),
        inverseJoinColumns = @JoinColumn(name = "HABILIDAD_ID")
    )
    private List<Habilidad> habilidades = new ArrayList<>();
    
    // Constructor vacío obligatorio
    public Scott2() {}
    
    // Constructor completo
    public Scott2(Long empno, String ename, String job, Integer mgr, Integer sal, Integer comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }
    
    // Getters y Setters
    public Long getEmpno() { return empno; }
    public void setEmpno(Long empno) { this.empno = empno; }
    
    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }
    
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    
    public Integer getMgr() { return mgr; }
    public void setMgr(Integer mgr) { this.mgr = mgr; }
    
    public Integer getSal() { return sal; }
    public void setSal(Integer sal) { this.sal = sal; }
    
    public Integer getComm() { return comm; }
    public void setComm(Integer comm) { this.comm = comm; }
    
    public Integer getDeptno() { return deptno; }
    public void setDeptno(Integer deptno) { this.deptno = deptno; }
    
    public List<Proyecto> getProyectos() { return proyectos; }
    public void setProyectos(List<Proyecto> proyectos) { this.proyectos = proyectos; }
    
    public List<Habilidad> getHabilidades() { return habilidades; }
    public void setHabilidades(List<Habilidad> habilidades) { this.habilidades = habilidades; }
    
    // Métodos helper
    public void addProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
        proyecto.setEmpleado(this);
    }
    
    public void removeProyecto(Proyecto proyecto) {
        proyectos.remove(proyecto);
        proyecto.setEmpleado(null);
    }
    
    public void addHabilidad(Habilidad habilidad) {
        habilidades.add(habilidad);
        habilidad.getEmpleados().add(this);
    }
    
    @Override
    public String toString() {
        return "Scott2 [empno=" + empno + ", ename=" + ename + ", job=" + job + "]";
    }
}
