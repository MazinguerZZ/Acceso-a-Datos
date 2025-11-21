package com.arquitecturajava.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Persona {

	@Id
	private String nombre;
	private int edad;

	public Persona() {
		super();

	}

	public Persona(String nombre, int edad) {
		super();
		setEdad(edad);
		setNombre(nombre);
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public int getEdad() {

		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;

	}

	@Override
	public int hashCode() {
//simplificacion
		return nombre.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
//simplificacion
		Persona nueva = (Persona) obj;
		return nueva.getNombre().equals(this.getNombre());

	}
}
