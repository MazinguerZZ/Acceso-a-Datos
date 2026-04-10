package com.amanecer.seguros.modelo;

import java.io.Serializable;
import jakarta.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.type.YesNoConverter;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the seguro database table.
 * 
 */
@Entity
@Table(name="seguro")
@NamedQuery(name="Seguro.findAll", query="SELECT s FROM Seguro s")
public class Seguro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	enum TipoSexo {HOMBRE,MUJER};
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String ape1;

	private String ape2;

	@Convert(converter=YesNoConverter.class)
	private Boolean casado;

	private Integer edad;

	@Convert(converter=YesNoConverter.class)
	private Boolean embarazada;

	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@Column(nullable=false, unique=true)
	private String nif;

	private String nombre;

	private Integer numHijos;

	//Como este enumerado lo almaceno como ordinal no me hace falta anotación
	private TipoSexo sexo;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="seguro", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Asistencia> asistencias;

	//uni-directional one-to-one association to Cobertura
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="coberturaId", referencedColumnName="id")
	private Cobertura cobertura;

	//uni-directional one-to-one association to Enfermedad
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="enfermedadId", referencedColumnName="id")
	private Enfermedad enfermedad;

	public Seguro() {
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getApe1() {
		return ape1;
	}



	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}



	public String getApe2() {
		return ape2;
	}



	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}



	public Boolean getCasado() {
		return casado;
	}



	public void setCasado(Boolean casado) {
		this.casado = casado;
	}



	public Integer getEdad() {
		return edad;
	}



	public void setEdad(Integer edad) {
		this.edad = edad;
	}



	public Boolean getEmbarazada() {
		return embarazada;
	}



	public void setEmbarazada(Boolean embarazada) {
		this.embarazada = embarazada;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public String getNif() {
		return nif;
	}



	public void setNif(String nif) {
		this.nif = nif;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Integer getNumHijos() {
		return numHijos;
	}



	public void setNumHijos(Integer numHijos) {
		this.numHijos = numHijos;
	}



	public TipoSexo getSexo() {
		return sexo;
	}



	public void setSexo(TipoSexo sexo) {
		this.sexo = sexo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setSeguro(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setSeguro(null);

		return asistencia;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	@Override
	public String toString() {
		return "Seguro [id=" + id + ", ape1=" + ape1 + ", ape2=" + ape2 + ", casado=" + casado + ", edad=" + edad
				+ ", embarazada=" + embarazada + ", fechaCreacion=" + fechaCreacion + ", nif=" + nif + ", nombre="
				+ nombre + ", numHijos=" + numHijos + ", sexo=" + sexo + ", cobertura=" + cobertura + ", enfermedad="
				+ enfermedad + "]";
	}

}