package models;

import java.util.Stack;
import java.util.Vector;

import interfaces.Empilhavel;
import models.abstracts.Carta;
import models.enums.Valor;

public class Fileira implements Empilhavel{

  private Stack<Carta> cartas = new Stack<Carta>();

  public Fileira (Vector<Carta> cartas) {
    this.cartas.addAll(cartas);
  }

  public void empilhar(Vector<Carta> cartas) {
    if (aceitaCarta(cartas.firstElement())){
      this.cartas.addAll(cartas);
    }
  }

  public Stack<Carta> desempilhar(int quantidade) {
    Stack<Carta> desempilhado = new Stack<Carta>();
    while(desempilhado.size() < quantidade) {
      desempilhado.push(this.cartas.pop());
    }
    return desempilhado;
  }

  public Carta olharCartaTopo() {
    if (this.cartas.isEmpty()) {
      return null;
    }
    return this.cartas.peek();
  }

  public void virarCartaTopo() {
    if (this.cartas.isEmpty()) return;

    if (!this.cartas.peek().estaVisivel()) {
      this.cartas.peek().setVisibilidade(true);
    }
  }

  public Stack<Carta> getCartas() {
    return this.cartas;
  }

  @Override
  public boolean aceitaCarta(Carta carta) {
    if (this.cartas.isEmpty()) {
      return carta.getValor() == Valor.REI;
    }
    boolean temValorMenor = carta.getValor().peso < this.cartas.peek().getValor().peso;
    boolean cartaVermelha = carta instanceof Copas || carta instanceof Ouro;
    boolean cartaPreta = carta instanceof Espadas || carta instanceof Paus;
    return temValorMenor && Boolean.logicalXor(cartaPreta, cartaVermelha);
  }
}
