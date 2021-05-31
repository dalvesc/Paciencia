package models.estruturas;


import java.util.Stack;
import java.util.Vector;
import interfaces.Empilhavel;
import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.enums.Valor;


/**
 * Esta classe é uma versão da Fundação que só aceita as cartas K (rei).
 * 
 * @author Adlla Katarine e Daniel Alves
 */
public class FundacaoEspecial extends Estrutura implements Empilhavel {
  public FundacaoEspecial() {
    super(new Vector<Carta>());
  }


  /**
   * 
   * Método que empilha a carta na fundação especial, caso seja aceita no monte.
   * 
   */
  @Override
  public void empilhar(Vector<Carta> cartas) {
    if (aceitaCarta(cartas.firstElement())) {
      this.cartas.addAll(cartas);
    }
  }

  /**
   * Este método não contempla a regra da fundação especial, retornando null por ser inutilizada, mas ser necessária
   * sua implentação por implementar de Empilhavel.
   * 
   * @return Stack<Carta> - retornando sempre null.
   */
  @Override
  public Stack<Carta> desempilhar(int quantidade) {
    return null;
  }

  /**
   * 
   * Método que verifica se a fundação aceita a carta (apenas se ela for um Rei).
   * 
   * @return boolean - true caso aceite.
   */
  @Override
  public boolean aceitaCarta(Carta carta) {
        return carta.getValor() == Valor.REI;
    }
}