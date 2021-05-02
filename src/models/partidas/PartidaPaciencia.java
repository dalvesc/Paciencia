package models.partidas;

import java.util.ArrayList;
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
import models.estruturas.Descarte;
import models.estruturas.Estoque;
import models.estruturas.Fundacao;
import models.estruturas.Tableau;

public class PartidaPaciencia implements Partida {
  private final int BARALHO_MAX_SIZE = 52;

  @Override
  public Vector<Estrutura> create() throws InvalidAttributeValueException {
    Stack<Carta> baralho = this.criarBaralho();
    Collections.shuffle(baralho);
    Vector<Estrutura> estruturas = new Vector<Estrutura>();
    estruturas.add(new Fundacao());
    estruturas.add(new Fundacao());
    estruturas.add(new Fundacao());
    estruturas.add(new Fundacao());
    for (int fileira = 0; fileira < 7; fileira++) {
      Stack<Carta> tableau = new Stack<Carta>();
      for (int linha = 0; linha < fileira + 1; linha++) {
        Carta c = baralho.pop();
        c.setVisibilidade(linha == fileira);
        tableau.push(c);
      }
      estruturas.add(new Tableau(tableau));
    }
    estruturas.add(new Descarte());
    estruturas.add(new Estoque(baralho));
    return estruturas;
  }

  private Stack<Carta> criarBaralho () throws InvalidAttributeValueException {
    Stack<Carta> baralho = new Stack<>();
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
    return baralho;
  }
}
