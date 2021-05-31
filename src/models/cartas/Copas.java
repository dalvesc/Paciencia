package models.cartas;


import javax.management.InvalidAttributeValueException;
import models.abstracts.Carta;


public class Copas extends Carta {

  public Copas(int valor) throws InvalidAttributeValueException {
    super(valor);
  }

  @Override
  public String getNomeNaipe() {
    return "♥";
  }
}