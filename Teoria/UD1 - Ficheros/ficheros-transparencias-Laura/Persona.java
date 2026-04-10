package transparencias;

/**
 *  @descrition Clase que representa una persona
 *	@author Laura
 *  @date 29/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class Persona {
	private String nombre;
	private String apellido;
	private Telefono telefono;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Telefono getTelefono() {
		return telefono;
	}
	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido
				+ ", telefono=" + telefono.toString() + "]";
	}
	
	

}


