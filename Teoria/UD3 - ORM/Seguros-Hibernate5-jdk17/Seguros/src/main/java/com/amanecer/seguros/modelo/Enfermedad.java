package com.amanecer.seguros.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.type.YesNoConverter;


/**
 * The persistent class for the enfermedad database table.
 * 
 */
@Entity
@Table(name="enfermedad")
@NamedQuery(name="Enfermedad.findAll", query="SELECT e FROM Enfermedad e")
public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Convert(converter=YesNoConverter.class)
	private Boolean corazon;

	@Convert(converter=YesNoConverter.class)
	private Boolean estomacal;

	@Convert(converter=YesNoConverter.class)
	@Column(name="rinyones")
	private Boolean riñones;
	
	@Convert(converter=YesNoConverter.class)
	private Boolean alergia;
	
	private String nombreAlergia;


	public Enfermedad() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Boolean getCorazon() {
		return corazon;
	}


	public void setCorazon(Boolean corazon) {
		this.corazon = corazon;
	}


	public Boolean getEstomacal() {
		return estomacal;
	}


	public void setEstomacal(Boolean estomacal) {
		this.estomacal = estomacal;
	}


	public Boolean getRiñones() {
		return riñones;
	}


	public void setRiñones(Boolean riñones) {
		this.riñones = riñones;
	}


	public Boolean getAlergia() {
		return alergia;
	}


	public void setAlergia(Boolean alergia) {
		this.alergia = alergia;
	}


	public String getNombreAlergia() {
		return nombreAlergia;
	}


	public void setNombreAlergia(String nombreAlergia) {
		this.nombreAlergia = nombreAlergia;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	



}