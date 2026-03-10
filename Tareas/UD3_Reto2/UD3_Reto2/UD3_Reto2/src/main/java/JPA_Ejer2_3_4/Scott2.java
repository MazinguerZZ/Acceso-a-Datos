package JPA_Ejer2_3_4;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "EMP2")
public class Scott2 {
    
    @Id
    @Column(name = "EMPNO")
    private Long empno;
    
    @Column(name = "ENAME", length = 10)
    private String ename;
    
    @Column(name = "JOB", length = 20)
    private String job;
    
    @Column(name = "MGR")
    private Integer mgr;
    
    @Column(name = "SAL")
    private Integer sal;
    
    @Column(name = "COMM")
    private Integer comm;
    
    @Column(name = "DEPTNO")
    private Integer deptno;
    
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Proyecto> proyectos = new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "EMP2_HABILIDAD",
        joinColumns = @JoinColumn(name = "EMPNO"),
        inverseJoinColumns = @JoinColumn(name = "HABILIDAD_ID")
    )
    private List<Habilidad> habilidades = new ArrayList<>();
    
    public Scott2() {}
    
    public Scott2(Long empno, String ename, String job, Integer mgr, 
                  Integer sal, Integer comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }
    
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
    
    public void removeHabilidad(Habilidad habilidad) {
        habilidades.remove(habilidad);
        habilidad.getEmpleados().remove(this);
    }
    
    @Override
    public String toString() {
        return "Scott2 [empno=" + empno + ", ename=" + ename + ", job=" + job + 
               ", sal=" + sal + ", deptno=" + deptno + "]";
    }
}