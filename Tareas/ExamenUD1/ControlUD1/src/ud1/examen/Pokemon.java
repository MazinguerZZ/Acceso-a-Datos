package ud1.examen;

import java.util.Objects;

public class Pokemon {
	int número;
	String nombre;
	String tipo1;
	String tipo2;
	int total;
	int hitPoints;
	int ataque;
	int defensa;
	int ataqueEspecial;
	int defensaEspecial;
	int velocidad;
	int generación;
	boolean legendario;
	
	public Pokemon() {}
	
	public Pokemon(int número, String nombre, String tipo1, String tipo2, int total, int hitPoints, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, int generación, boolean legendario) {
		super();
		this.número = número;
		this.nombre = nombre;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.total = total;
		this.hitPoints = hitPoints;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.generación = generación;
		this.legendario = legendario;
	}
	
	public int getId() {
		return número;
	}
	public void setNúmero(int número) {
		this.número = número;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo1() {
		return tipo1;
	}
	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}
	public String getTipo2() {
		return tipo2;
	}
	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getHitPoints() {
		return hitPoints;
	}
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}
	public void setAtaqueEspecial(int ataqueEspecial) {
		ataqueEspecial = ataqueEspecial;
	}
	public int getDefensaEspecial() {
		return defensaEspecial;
	}
	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getGeneración() {
		return generación;
	}
	public void setGeneración(int generación) {
		this.generación = generación;
	}
	public boolean isLegendario() {
		return legendario;
	}
	public void setLegendario(boolean legendario) {
		this.legendario = legendario;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ataque, ataqueEspecial, defensa, defensaEspecial, generación, hitPoints, número, legendario,
				nombre, tipo1, tipo2, total, velocidad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return ataque == other.ataque && ataqueEspecial == other.ataqueEspecial && defensa == other.defensa
				&& defensaEspecial == other.defensaEspecial && generación == other.generación
				&& hitPoints == other.hitPoints && número == other.número && legendario == other.legendario
				&& Objects.equals(nombre, other.nombre) && Objects.equals(tipo1, other.tipo1)
				&& Objects.equals(tipo2, other.tipo2) && total == other.total && velocidad == other.velocidad;
	}

	@Override
	public String toString() {
		return "Pokemon [Número=" + número + ", Nombre=" + nombre + ", Tipo1=" + tipo1 + ", Tipo2=" + tipo2 + ", Total=" + total
				+ ", HitPoints=" + hitPoints + ", Ataque=" + ataque + ", Defensa=" + defensa + ", AtaqueEspecial="
				+ ataqueEspecial + ", DefensaEspecial=" + defensaEspecial + ", Velocidad=" + velocidad + ", Generación="
				+ generación + ", Legendario=" + legendario + "]";
	}


}
