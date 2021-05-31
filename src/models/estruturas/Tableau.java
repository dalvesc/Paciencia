package models.estruturas;


import java.util.Stack;
import java.util.Vector;
import interfaces.Empilhavel;
import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.cartas.Copas;
import models.cartas.Ouros;
import models.enums.Valor;


public class Tableau extends Estrutura implements Empilhavel{
  public Tableau (Vector<Carta> cartas) {
    super(cartas);
  }


  @Override
  public void empilhar(Vector<Carta> cartas) {
    if (aceitaCarta(cartas.firstElement())){
      this.cartas.addAll(cartas);
    }
  }

  @Override
  public Stack<Carta> desempilhar(int quantidade) {
    Stack<Carta> desempilhado = new Stack<Carta>();
    while(desempilhado.size() < quantidade && !this.cartas.isEmpty()) {
      desempilhado.push(this.cartas.pop());
    }
    return desempilhado;
  }

  public void virarCartaTopo() {
    if (this.cartas.isEmpty()) return;

    if (!this.cartas.peek().estaVisivel()) {
      this.cartas.peek().setVisibilidade(true);
    }
  }

  @Override
  public boolean aceitaCarta(Carta carta) {
    if (this.cartas.isEmpty()) {
      return carta.getValor() == Valor.REI;
    }
    return compararCorEValor(carta);
  }

  public boolean aceitaCarta_OutraRegra(Carta carta){
    if (this.cartas.isEmpty()) {
      return true;
    } return compararCorEValor(carta);
  }

  /**
   * 
   * 
   * 
   * @param carta
   * @return boolean
   */
  private boolean compararCorEValor(Carta carta){
    boolean temValorMenor = carta.getValor().peso == this.cartas.peek().getValor().peso - 1;

    boolean cartaAnteriorIsVermelha = this.cartas.peek() instanceof Copas || this.cartas.peek() instanceof Ouros;
    boolean cartaAtualIsVermelha = carta instanceof Copas || carta instanceof Ouros;
    //comparação cores
    return temValorMenor && Boolean.logicalXor(cartaAnteriorIsVermelha, cartaAtualIsVermelha);
  }
}