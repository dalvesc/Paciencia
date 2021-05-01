package models.baralhos;

import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import interfaces.Baralho;
import models.abstracts.Carta;
import models.cartas.Copas;
import models.cartas.Espadas;
import models.cartas.Ouro;
import models.cartas.Paus;

public class BaralhoPaciencia implements Baralho {
  private final int BARALHO_MAX_SIZE = 52;
  private ArrayList<Carta> baralho = new ArrayList<Carta>(BARALHO_MAX_SIZE);

  @Override
  public ArrayList<Carta> create() throws InvalidAttributeValueException {
    for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
      this.baralho.add(new Copas(i));
    }
    for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
      this.baralho.add(new Espadas(i));
    }
    for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
      this.baralho.add(new Paus(i));
    }
    for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
      this.baralho.add(new Ouro(i));
    }
    return this.baralho;
  }
}
