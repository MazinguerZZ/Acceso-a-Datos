package transparencias.alias;

/**
 * @descrition
 * @author Laura
 * @date 30/4/2015
 * @version 1.0
 * @license GPLv3
 */

public class Entrada {
	private String titulo;
	private String descripcion;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Entrada(String titulo, String descripcion) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

}
