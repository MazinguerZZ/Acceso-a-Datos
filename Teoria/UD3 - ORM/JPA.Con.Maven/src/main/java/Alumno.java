
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Alumnos")
public class Alumno {

	@Id
	private String DNI;
	private String nombre;
	private String apellidos;
	private int CP;
	
	public Alumno() {
		super();

	}

	public Alumno(String dNI, String nombre, String apellidos, int cP) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		CP = cP;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getCP() {
		return CP;
	}

	public void setCP(int cP) {
		CP = cP;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CP, DNI, apellidos, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return CP == other.CP && Objects.equals(DNI, other.DNI) && Objects.equals(apellidos, other.apellidos)
				&& Objects.equals(nombre, other.nombre);
	}
	
	
	
	
}
