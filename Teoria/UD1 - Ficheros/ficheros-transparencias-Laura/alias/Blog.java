package transparencias.alias;

import java.util.ArrayList;
import java.util.List;

/**
 *  @descrition
 *	@author Laura
 *  @date 30/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class Blog {
	private Autor escritor;
	private List<Entrada> entradas;
	
	public Blog(Autor escritor) {
		super();
		this.escritor = escritor;
		entradas=new ArrayList<Entrada>();
	}
	
	public void add(Entrada entrada){
		entradas.add(entrada);
		
	}

	public List<Entrada> getContenido(){
		return this.entradas;
	}
	
	public Autor getAutor(){
		return this.escritor;
	}

}


