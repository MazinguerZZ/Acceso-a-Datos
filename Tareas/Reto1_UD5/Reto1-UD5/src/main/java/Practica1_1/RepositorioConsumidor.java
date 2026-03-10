package Practica1_1;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioConsumidor extends MongoRepository<Consumidor, String> {

  public List<Consumidor> findByFirstName(String firstName);
  public List<Consumidor> findByLastName(String lastName);

}
