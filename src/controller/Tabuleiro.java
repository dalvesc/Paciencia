package controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.baralhos.BaralhoPaciencia;
import models.estruturas.Descarte;
import models.estruturas.Estoque;
import models.estruturas.Fileira;
import models.estruturas.Fundacao;

public class Tabuleiro {
  private static Tabuleiro tabuleiro = null;
  private Vector<Estrutura> elementosPartida;
  private int qtdCartasEstoque = 1;

  private Tabuleiro () throws InvalidAttributeValueException {
    Stack<Carta> baralho = new Stack<Carta>();
    baralho.addAll(new BaralhoPaciencia().create());
    Collections.shuffle(baralho);
    this.elementosPartida = new Vector<Estrutura>();
    this.elementosPartida.add(new Fundacao());
    this.elementosPartida.add(new Fundacao());
    this.elementosPartida.add(new Fundacao());
    this.elementosPartida.add(new Fundacao());
    for (int fileira = 0; fileira < 7; fileira++) {
      Stack<Carta> tableau = new Stack<Carta>();
      for (int linha = 0; linha < fileira + 1; linha++) {
        Carta c = baralho.pop();
        c.setVisibilidade(linha == fileira);
        tableau.push(c);
      }
      this.elementosPartida.add(new Fileira(tableau));
    }
    this.elementosPartida.add(new Descarte());
    this.elementosPartida.add(new Estoque(baralho));
  }

  public static Tabuleiro getInstance() {
    if (tabuleiro == null) {
      try {
        tabuleiro = new Tabuleiro();
      } catch (InvalidAttributeValueException e) {
        e.printStackTrace();
      }
    }
    return tabuleiro;
  }

  public static void resetInstance() {
    try {
      tabuleiro = new Tabuleiro();
    } catch (InvalidAttributeValueException e) {
      e.printStackTrace();
    }
  }

  public void moverCarta(int de, int para) {
    Estrutura origem = this.elementosPartida.get(de);
    Estrutura destino = this.elementosPartida.get(para);

    if (origem instanceof Estoque) {
      if (destino instanceof Descarte) {
        this.elementosPartida.get(para).empilhar(((Estoque) this.elementosPartida.get(de)).desempilhar(qtdCartasEstoque));
      } 
    } else if (origem instanceof Descarte) {
      if (destino instanceof Estoque) {
        this.elementosPartida.get(para).empilhar(((Descarte) this.elementosPartida.get(de)).esvaziar());
      } else if (destino instanceof Fileira) {
        if (((Fileira) destino).aceitaCarta(origem.verCartaTopo())){
          this.elementosPartida.get(para).empilhar(((Descarte) this.elementosPartida.get(de)).desempilhar(1));
        }
      }
    } else if (origem instanceof Fileira) {
      if (destino instanceof Fileira) {
        int quantidadeRejeitada = 0;
        int quantidadeDesempilhar = 0;
        Vector<Carta> aMover = origem.getCartas();
        for (int i = 0; i < aMover.size(); i++) {
          Carta percorrida = aMover.get(i);
          if (percorrida.estaVisivel() && ((Fileira) destino).aceitaCarta(percorrida)) {
            break;
          } else {
            quantidadeRejeitada += 1;
          }
        }
        quantidadeDesempilhar = aMover.size() - quantidadeRejeitada;
        Stack<Carta> desempilhado = this.elementosPartida.get(de).desempilhar(quantidadeDesempilhar);
        Collections.reverse(desempilhado);
        this.elementosPartida.get(para).empilhar(desempilhado);
      } else if (destino instanceof Fundacao) {
        if (((Fundacao) destino).aceitaCarta(origem.verCartaTopo())){
          this.elementosPartida.get(para).empilhar(((Fileira) this.elementosPartida.get(de)).desempilhar(1));
        }
      }
    } else if (origem instanceof Fundacao) {
      if (destino instanceof Fileira) {
        if (((Fileira) destino).aceitaCarta(origem.verCartaTopo())){
          this.elementosPartida.get(para).empilhar(((Fundacao) this.elementosPartida.get(de)).desempilhar(1));
        }
      }
    }
  }

  public void revelarTopo(int fileira) {
    Estrutura estrutura = this.elementosPartida.get(fileira);
    if (estrutura instanceof Fileira) {
      ((Fileira) this.elementosPartida.get(fileira)).virarCartaTopo();
    }
  }

  public void mudarDificuldade() {
    this.qtdCartasEstoque = this.qtdCartasEstoque == 1 ? 3 : 1;
  }

  public void exibir() {
    Iterator<Estrutura> it = this.elementosPartida.iterator();
    int position = 0;
    while(it.hasNext()) {
      Estrutura estrutura = it.next();
      if (estrutura instanceof Descarte) {
        this.listarCartas("Descarte - " + position, estrutura.getCartas());
      } else if (estrutura instanceof Estoque) {
        this.listarCartas("Estoque - " + position, estrutura.getCartas());
      } else if (estrutura instanceof Fileira) {
        this.listarCartas("Fileira - " + position, estrutura.getCartas());
      } else if (estrutura instanceof Fundacao) {
        this.listarCartas("Fundacao - " + position, estrutura.getCartas());
      }
      position++;
    }
  }

  private void listarCartas(String title, Vector<Carta> cartas) {
    StringBuilder sb = new StringBuilder(title + " = ");
    for(int i = 0; i < cartas.size(); i++) {
      Carta carta = cartas.get(i);
      if (carta.estaVisivel()) {
        sb.append(carta.getValor() + " de " + carta.getNomeNaipe());
      } else {
        sb.append("[<>]");
      }
      if (i < cartas.size() - 1) {
        sb.append(", ");
      }
    }
    if (cartas.isEmpty()) {
      sb.append("Vazio");
    }
    System.out.println(sb.toString().toUpperCase());
  }
}
