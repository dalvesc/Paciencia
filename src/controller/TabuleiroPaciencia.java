package controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.enums.Valor;
import models.estruturas.Descarte;
import models.estruturas.Estoque;
import models.estruturas.Tableau;
import models.partidas.PartidaPaciencia;
import models.estruturas.Fundacao;

public class TabuleiroPaciencia {
  private static TabuleiroPaciencia tabuleiro = null;
  private Vector<Estrutura> elementosPartida;
  private int qtdCartasEstoque = 1;

  private TabuleiroPaciencia () throws InvalidAttributeValueException {
    this.elementosPartida = new PartidaPaciencia().create();
  }

  public static TabuleiroPaciencia getInstance() {
    if (tabuleiro == null) {
      try {
        tabuleiro = new TabuleiroPaciencia();
      } catch (InvalidAttributeValueException e) {
        e.printStackTrace();
      }
    }
    return tabuleiro;
  }

  public static void resetInstance() {
    try {
      tabuleiro = new TabuleiroPaciencia();
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
      } else if (destino instanceof Tableau) {
        if (((Tableau) destino).aceitaCarta(origem.verCartaTopo())){
          this.elementosPartida.get(para).empilhar(((Descarte) this.elementosPartida.get(de)).desempilhar());
        }
      }
    } else if (origem instanceof Tableau) {
      if (destino instanceof Tableau) {
        int quantidadeRejeitada = 0;
        int quantidadeDesempilhar = 0;
        Vector<Carta> aMover = origem.getCartas();
        for (int i = 0; i < aMover.size(); i++) {
          Carta percorrida = aMover.get(i);
          if (percorrida.estaVisivel() && ((Tableau) destino).aceitaCarta(percorrida)) {
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
          this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
        }
      }
    } else if (origem instanceof Fundacao) {
      if (destino instanceof Tableau) {
        if (((Tableau) destino).aceitaCarta(origem.verCartaTopo())){
          this.elementosPartida.get(para).empilhar(((Fundacao) this.elementosPartida.get(de)).desempilhar());
        }
      }
    }
  }

  public void revelarTopo(int fileira) {
    Estrutura estrutura = this.elementosPartida.get(fileira);
    if (estrutura instanceof Tableau) {
      ((Tableau) this.elementosPartida.get(fileira)).virarCartaTopo();
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
      } else if (estrutura instanceof Tableau) {
        this.listarCartas("Tableau - " + position, estrutura.getCartas());
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
        sb.append(carta.getValor() + " " +carta.getNomeNaipe());
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

  public boolean checarVitoria() {
    Iterator<Estrutura> it = this.elementosPartida.iterator();
    int fundacoesCompletas = 0;
    while (it.hasNext()) {
      Estrutura estrutura = it.next();
      if (estrutura instanceof Fundacao) {
        Stack<Carta> cartas = ((Fundacao) estrutura).getCartas();
        if (cartas.firstElement().getValor() == Valor.AS && cartas.lastElement().getValor() == Valor.REI) {
          fundacoesCompletas += 1;
        }
      }
    }
    return fundacoesCompletas == 4;
  }
}
