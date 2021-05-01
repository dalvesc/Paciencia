package models.cartas;

import javax.management.InvalidAttributeValueException;
import models.abstracts.Carta;

public class Paus extends Carta {
  
  public Paus(int valor) throws InvalidAttributeValueException {
    super(valor);
  }

  @Override
  public String getNomeNaipe() {
    return "paus";
  }

}
