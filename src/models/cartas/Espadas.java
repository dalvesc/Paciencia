package models.cartas;


import javax.management.InvalidAttributeValueException;
import models.abstracts.Carta;


public class Espadas extends Carta {
  public Espadas(int valor) throws InvalidAttributeValueException {
    super(valor);
  }

  @Override
  public String getNomeNaipe() {
    return "♠";
  }
}