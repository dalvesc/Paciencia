package models.estruturas;

import java.util.Stack;
import java.util.Vector;

import interfaces.Empilhavel;
import models.abstracts.Carta;
import models.abstracts.Estrutura;

public class Fundacao extends Estrutura implements Empilhavel {

  public Fundacao() {
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
    Stack<Carta> desempilhado = new Stack<Carta>();
    desempilhado.push(this.desempilhar());
    return desempilhado;
  }

  @Override
  public boolean aceitaCarta(Carta carta) {
    boolean mesmoNaipe = this.cartas.peek().getClass().equals(carta.getClass());
    boolean valorMaior = carta.getValor().peso == this.cartas.peek().getValor().peso + 1;
    return mesmoNaipe && valorMaior;
  }
}
