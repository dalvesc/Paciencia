package models.cartas;

import javax.management.InvalidAttributeValueException;
import models.abstracts.Carta;

public class Ouros extends Carta {

  public Ouros(int valor) throws InvalidAttributeValueException {
    super(valor);
  }

  @Override
  public String getNomeNaipe() {
    return "ouros";
  }
  
}
