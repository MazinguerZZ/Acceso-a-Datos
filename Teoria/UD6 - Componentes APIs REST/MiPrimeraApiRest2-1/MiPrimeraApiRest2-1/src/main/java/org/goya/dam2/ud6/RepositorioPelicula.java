package org.goya.dam2.ud6;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPelicula extends CrudRepository<Pelicula, Long>{

}
