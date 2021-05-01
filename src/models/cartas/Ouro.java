package models.cartas;

import javax.management.InvalidAttributeValueException;
import models.abstracts.Carta;

public class Ouro extends Carta {

  public Ouro(int valor) throws InvalidAttributeValueException {
    super(valor);
  }

  @Override
  public String getNomeNaipe() {
    return "ouro";
  }
  
}
