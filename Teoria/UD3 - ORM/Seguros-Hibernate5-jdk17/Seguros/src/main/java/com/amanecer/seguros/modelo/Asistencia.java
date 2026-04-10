package com.amanecer.seguros.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the asistencia database table.
 * 
 */
@Entity
@Table(name="asistencia")
@NamedQuery(name="Asistencia.findAll", query="SELECT a FROM Asistencia a")
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	@Lob
	private String explicacion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.TIME)
	private Date hora;

	private BigDecimal importe;

	private String lugar;

	private String tipoAsistencia;

	//bi-directional many-to-one association to Seguro
	@ManyToOne
	@JoinColumn(name="seguroId")
	private Seguro seguro;

	public Asistencia() {
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getExplicacion() {
		return explicacion;
	}


	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Date getHora() {
		return hora;
	}


	public void setHora(Date hora) {
		this.hora = hora;
	}


	public BigDecimal getImporte() {
		return importe;
	}


	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public String getTipoAsistencia() {
		return tipoAsistencia;
	}


	public void setTipoAsistencia(String tipoAsistencia) {
		this.tipoAsistencia = tipoAsistencia;
	}


	public Seguro getSeguro() {
		return seguro;
	}


	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}


	@Override
	public String toString() {
		return "Asistencia [id=" + id + ", descripcion=" + descripcion + ", explicacion=" + explicacion + ", fecha="
				+ fecha + ", hora=" + hora + ", importe=" + importe + ", lugar=" + lugar + ", tipoAsistencia="
				+ tipoAsistencia + ", seguro=" + seguro + "]";
	}

}