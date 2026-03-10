// IMPORTANTE: Este archivo NO debe tener package
// Debe estar en src/main/java/Scott.java (sin paquete)

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EMP")
public class Scott {

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
    
    // Constructor vacío REQUERIDO
    public Scott() {
    }
    
    // Constructor con parámetros
    public Scott(Long empno, String ename, String job, Integer mgr, 
                 Integer sal, Integer comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    // Getters y Setters
    public Long getEmpno() {
        return empno;
    }

    public void setEmpno(Long empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Scott [empno=" + empno + ", ename=" + ename + ", job=" + job + 
               ", mgr=" + mgr + ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scott scott = (Scott) o;
        return Objects.equals(empno, scott.empno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno);
    }
}