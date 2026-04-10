package com.amanecer.seguros.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.type.TrueFalseConverter;


/**
 * The persistent class for the cobertura database table.
 * 
 */
@Entity
@Table(name="cobertura")
@NamedQuery(name="Cobertura.findAll", query="SELECT c FROM Cobertura c")
public class Cobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Convert(converter=TrueFalseConverter.class)
	private Boolean dental;

	private Boolean fecundacionInVitro;

	private Boolean oftalmologia;

	public Cobertura() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDental() {
		return dental;
	}

	public void setDental(Boolean dental) {
		this.dental = dental;
	}

	public Boolean getFecundacionInVitro() {
		return fecundacionInVitro;
	}

	public void setFecundacionInVitro(Boolean fecundacionInVitro) {
		this.fecundacionInVitro = fecundacionInVitro;
	}

	public Boolean getOftalmologia() {
		return oftalmologia;
	}

	public void setOftalmologia(Boolean oftalmologia) {
		this.oftalmologia = oftalmologia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


}