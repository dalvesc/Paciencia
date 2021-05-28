package models.estruturas;

import java.util.Stack;
import java.util.Vector;

import interfaces.Empilhavel;
import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.enums.Valor;

/**
 * Esta classe é uma versão da Fundação que só aceita as cartas K (rei).
 */

public class FundacaoEspecial extends Estrutura implements Empilhavel {
  public FundacaoEspecial() {
    super(new Vector<Carta>());
  }

  @Override
  public void empilhar(Vector<Carta> cartas) {
    if (aceitaCarta(cartas.firstElement())) {
      this.cartas.addAll(cartas);
    }
  }

  @Override
  public Stack<Carta> desempilhar(int quantidade) {
    return null;
  }

  @Override
  public boolean aceitaCarta(Carta carta) {
        System.out.println("CARTAAAAAAAAAAA: " + carta.getValor());
        return carta.getValor() == Valor.REI;
    }
}
