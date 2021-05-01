package models.abstracts;

import javax.management.InvalidAttributeValueException;
import models.enums.Valor;

public abstract class Carta {
  protected Valor valor;
  protected boolean visivel = false;

  public Carta (int valor) throws InvalidAttributeValueException {
    if (valor < 0 || valor > 12) {
      throw new InvalidAttributeValueException();
    }
    this.valor = Valor.values()[valor];
  }

  public Valor getValor() {
    return valor;
  }

  public boolean estaVisivel() {
    return this.visivel;
  }

  public void setVisibilidade(boolean visivel){
    this.visivel = visivel;
  }

  public void exibir() {
    System.out.println(this.getValor() + " de " + this.getNomeNaipe());
  }

  public abstract String getNomeNaipe();
}
