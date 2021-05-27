package models.partidas;

import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import interfaces.Partida;
import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.cartas.Copas;
import models.cartas.Espadas;
import models.cartas.Ouros;
import models.cartas.Paus;
import models.estruturas.Estoque;
import models.estruturas.Fundacao;
import models.estruturas.FundacaoEspecial;
import models.estruturas.Tableau;

public class PartidaBigBertha implements Partida {
  private final int BARALHO_MAX_SIZE = 52;
  private final int QNTD_BARALHO = 2;

  @Override
  public Vector<Estrutura> create() throws InvalidAttributeValueException {
    Stack<Carta> baralho = this.criarBaralho();
    Collections.shuffle(baralho);
    Vector<Estrutura> estruturas = new Vector<Estrutura>();
    for (int i = 0; i < 8; i++) {
        estruturas.add(new Fundacao());
    }
    estruturas.add(new FundacaoEspecial());
    for (int fileira = 0; fileira < 15; fileira++) {
      Stack<Carta> tableau = new Stack<Carta>();
      for (int linha = 0; linha < 6; linha++) {
        Carta c = baralho.pop();
        c.setVisibilidade(true);
        tableau.push(c);
      }
      estruturas.add(new Tableau(tableau));
    }
    revelarUltimasCartasEstoque(baralho);
    estruturas.add(new Estoque(baralho));
    return estruturas;
  }

  private Stack<Carta> criarBaralho () throws InvalidAttributeValueException {
    Stack<Carta> baralho = new Stack<>();
    for (int j = 0; j < QNTD_BARALHO; j++) {
        for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
            baralho.add(new Copas(i));
          }
          for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
            baralho.add(new Espadas(i));
          }
          for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
            baralho.add(new Paus(i));
          }
          for (int i = 0; i < BARALHO_MAX_SIZE / 4; i++) {
            baralho.add(new Ouros(i));
          }
    } return baralho;
  }

  /**
   * 
   * Método que revela as 3 últimas cartas do estoque.
   * 
   * @param estoque pilha de cartas do estoque.
   */
  private void revelarUltimasCartasEstoque(Stack<Carta> estoque){
    int qndtCartasReveladas = 3;
    for (int i = estoque.size(); i > estoque.size() - qndtCartasReveladas; i--) {
      estoque.get(i-1).setVisibilidade(true);
    }
  }
}