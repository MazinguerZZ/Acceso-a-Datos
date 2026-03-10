package Practica1_1;

import org.springframework.data.annotation.Id;

public class Consumidor {

  @Id
  public String id;

  public String firstName;
  public String lastName;

  public Consumidor() {}

  public Consumidor(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%s, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

}
