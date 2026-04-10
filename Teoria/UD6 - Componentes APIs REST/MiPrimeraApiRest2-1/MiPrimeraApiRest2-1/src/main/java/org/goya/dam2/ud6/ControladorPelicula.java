package org.goya.dam2.ud6;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

@RestController
public class ControladorPelicula {
	RepositorioPelicula repositorioPelicula;
	
	public ControladorPelicula(RepositorioPelicula repositorioPelicula) {
		this.repositorioPelicula = repositorioPelicula;
		//repositorioPelicula.save(new Pelicula("drama", "Los lunes al sol", "Amenabar"));
		//repositorioPelicula.save(new Pelicula("comedia", "El gran Lebowsky", "Hermanos Cohen"));

	}
	
	@PostMapping("/peliculas")
	public Pelicula postPelicula( @RequestBody Pelicula pelicula) {
		return repositorioPelicula.save(pelicula);
	}
	
	@GetMapping("/peliculas")
	List<Pelicula> getAll() {
		return (List<Pelicula>) repositorioPelicula.findAll();
	}
	
	@GetMapping("/peliculas/{id}")
	Pelicula getOne(@PathVariable(name="id") Long id) {
		return repositorioPelicula.findById(id).orElse(null);
	}

}
