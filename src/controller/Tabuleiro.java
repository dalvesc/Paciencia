package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
  private List<Fileira> tableaus = new ArrayList<Fileira>(7);
  private List<Fundacao> fundacoes = new ArrayList<Fundacao>(4);
  private Vector<Estrutura> estruturas;
  private Estoque estoque;
  private Descarte descarte;

  private Tabuleiro () throws InvalidAttributeValueException {
    Stack<Carta> baralho = new Stack<Carta>();
    baralho.addAll(new BaralhoPaciencia().create());
    Collections.shuffle(baralho);

    this.estruturas = new Vector<Estrutura>();
    this.estruturas.add(new Fundacao());
    this.estruturas.add(new Fundacao());
    this.estruturas.add(new Fundacao());
    this.estruturas.add(new Fundacao());
    for (int fileira = 0; fileira < 7; fileira++) {
      Stack<Carta> tableau = new Stack<Carta>();
      for (int linha = 0; linha < fileira + 1; linha++) {
        Carta c = baralho.pop();
        c.setVisibilidade(linha == fileira);
        tableau.push(c);
      }
      this.estruturas.add(new Fileira(tableau));
      this.tableaus.add(new Fileira(tableau));
    }
    this.estruturas.add(new Descarte());
    this.estruturas.add(new Estoque(baralho));
    this.descarte = new Descarte();
    this.estoque = new Estoque(baralho);
    this.fundacoes.add(new Fundacao());
    this.fundacoes.add(new Fundacao());
    this.fundacoes.add(new Fundacao());
    this.fundacoes.add(new Fundacao());
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

  public void virarDoEstoque(int quantidade) {
    if (this.estoque.getCartas().isEmpty()) {
      System.out.println("Estoque vazio");
      return;
    }
    this.descarte.empilhar(this.estoque.desempilhar(quantidade));
  }

  public void moverCarta(int de, int para) {
    Estrutura origem = this.estruturas.get(de);
    Estrutura destino = this.estruturas.get(para);

    if (origem instanceof Estoque) {
      if (destino instanceof Descarte) {
        this.estruturas.get(para).empilhar(((Estoque) this.estruturas.get(de)).desempilhar(1)); //1 ou 3
      } 
    } else if (origem instanceof Descarte) {
      if (destino instanceof Estoque) {
        this.estruturas.get(para).empilhar(((Descarte) this.estruturas.get(de)).esvaziar());
      } else if (destino instanceof Fileira) {
        if (((Fileira) destino).aceitaCarta(origem.verCartaTopo())){
          this.estruturas.get(para).empilhar(((Descarte) this.estruturas.get(de)).desempilhar(1));
        }
      }
    } else if (origem instanceof Fileira) {
      if (destino instanceof Fileira) {

      } else if (destino instanceof Fundacao) {

      }
    } else if (origem instanceof Fundacao) {

    }
    // int quantidadeRejeitada = 0;
    // int quantidadeDesempilhar = 0;
    // Vector<Carta> aMover = tableaus.get(de).getCartas();

    // for (int i = 0; i < aMover.size(); i++) {
    //   Carta percorrida = aMover.get(i);
    //   if (percorrida.estaVisivel() && tableaus.get(para).aceitaCarta(percorrida)) {
    //     break;
    //   } else {
    //     quantidadeRejeitada += 1;
    //   }
    // }

    // quantidadeDesempilhar = aMover.size() - quantidadeRejeitada;
    // Stack<Carta> desempilhado = this.tableaus.get(de).desempilhar(quantidadeDesempilhar);
    // Collections.reverse(desempilhado);
    // this.tableaus.get(para).empilhar(desempilhado);
  }

  public void moverParaFundacao(int fileira, int fundacao) {

  }

  public void pegarDoDescarte(int fileira) {
    Carta carta = this.descarte.verCartaTopo();
    Fileira tableau = this.tableaus.get(fileira);
    if (tableau.aceitaCarta(carta)) {
      this.tableaus.get(fileira).empilhar(this.descarte.desempilhar(1));
    }
  }

  public void esvaziarDescarte() {
    this.estoque.empilhar(this.descarte.esvaziar());
  }

  public void revelarTopo(int fileira) {
    this.tableaus.get(fileira).virarCartaTopo();
  }

  public void exibir() {
    Iterator<Estrutura> it = this.estruturas.iterator();
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
