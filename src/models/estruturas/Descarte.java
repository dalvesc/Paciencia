package models.estruturas;

import java.util.Stack;
import java.util.Vector;

import models.abstracts.Carta;
import models.abstracts.Estrutura;

public class Descarte extends Estrutura{
  
  public Descarte () {
    super(new Stack<Carta>());
  }

  @Override
  public void empilhar(Vector<Carta> cartas) {
    this.cartas.addAll(cartas);
  }

  public Stack<Carta> esvaziar() {
    return this.desempilhar(this.cartas.size());
  }

  @Override
  public Stack<Carta> desempilhar(int quantidade) {
    Stack<Carta> desempilhado = new Stack<Carta>();
    while(desempilhado.size() < quantidade && !this.cartas.isEmpty()) {
      desempilhado.push(this.cartas.pop());
    }
    return desempilhado;
  }
}
