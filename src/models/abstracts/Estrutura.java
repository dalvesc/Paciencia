package models.abstracts;


import java.util.Stack;
import java.util.Vector;


public abstract class Estrutura {
  protected Stack<Carta> cartas = new Stack<Carta>();
  
  protected Estrutura(Vector<Carta> cartas) {
    this.cartas.addAll(cartas);
  }

  public abstract void empilhar(Vector<Carta> cartas);
  public abstract Stack<Carta> desempilhar(int quantidade);
  
  public Stack<Carta> desempilhar() {
    if (this.cartas.isEmpty()) {
      return new Stack<Carta>();
    }
    return this.desempilhar(1);
  }

  public Carta verCartaTopo() {
    if (this.cartas.isEmpty()) {
      return null;
    }
    return this.cartas.peek();
  }

  public Stack<Carta> getCartas() {
    return this.cartas;
  }
}