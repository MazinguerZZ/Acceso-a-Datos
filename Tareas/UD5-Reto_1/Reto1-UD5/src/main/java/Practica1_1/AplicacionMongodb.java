package Practica1_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
public class AplicacionMongodb implements CommandLineRunner {

  @Autowired
  private RepositorioConsumidor repository;

  public static void main(String[] args) {
    SpringApplication.run(AplicacionMongodb.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // repository.deleteAll();

    // save a couple of customers
    repository.save(new Consumidor("Alice", "Smith"));
    repository.save(new Consumidor("Bob", "Smith"));

    // fetch all customers
    System.out.println("Los consumidores encontrados:():");
    System.out.println("-------------------------------");
    for (Consumidor customer : repository.findAll()) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer
    System.out.println("Consumidores encontrados con su nombre('Alice'):");
    System.out.println("--------------------------------");
    System.out.println(repository.findByFirstName("Alice"));

    System.out.println("Consumidores encontrados con su apellido('Smith'):");
    System.out.println("--------------------------------");
    for (Consumidor customer : repository.findByLastName("Smith")) {
      System.out.println(customer);
    }

  }

}