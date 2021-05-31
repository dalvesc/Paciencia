package models.estruturas;


import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import models.abstracts.Carta;
import models.abstracts.Estrutura;


public class Estoque extends Estrutura {
  public Estoque (Vector<Carta> cartas) {
    super(cartas);
  }


  public void empilhar(Vector<Carta> cartas) {
    this.cartas.addAll(cartas);
    Iterator<Carta> it = this.cartas.iterator();
    while (it.hasNext()) {
      it.next().setVisibilidade(false);
    }
  }

  public Stack<Carta> desempilhar(int quantidade) {
    Stack<Carta> desempilhado = new Stack<Carta>();
    while(desempilhado.size() < quantidade && !this.cartas.isEmpty()) {
      Carta carta = this.cartas.pop();
      carta.setVisibilidade(true);
      desempilhado.push(carta);
    }
    return desempilhado;
  }
}