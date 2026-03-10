package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.List; // ¡Añadir este import!

@RepositoryRestResource(path = "bibliotecas", collectionResourceRel = "bibliotecas")
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
    
    @RestResource(path = "por-ciudad", rel = "porCiudad")
    List<Biblioteca> findByCiudad(String ciudad);
    
    @RestResource(path = "por-anio", rel = "porAnio")
    List<Biblioteca> findByAnioFundacion(Integer anioFundacion);
    
    @RestResource(path = "por-nombre", rel = "porNombre")
    List<Biblioteca> findByNombreContaining(String nombre);
}