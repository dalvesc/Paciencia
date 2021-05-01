package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import interfaces.Empilhavel;
import models.abstracts.Carta;
import models.baralhos.BaralhoPaciencia;
import models.estruturas.Descarte;
import models.estruturas.Estoque;
import models.estruturas.Fileira;
import models.estruturas.Fundacao;

public class Tabuleiro {
  private static Tabuleiro tabuleiro = null;
  private List<Fileira> tableaus = new ArrayList<Fileira>(7);
  private List<Fundacao> fundacoes = new ArrayList<Fundacao>(4);
  private List<Empilhavel> empilhaveis = new ArrayList<Empilhavel>();
  private Estoque estoque;
  private Descarte descarte = new Descarte();

  private Tabuleiro () throws InvalidAttributeValueException {
    Stack<Carta> baralho = new Stack<Carta>();
    baralho.addAll(new BaralhoPaciencia().create());
    Collections.shuffle(baralho);
    for (int fileira = 0; fileira < 7; fileira++) {
      Stack<Carta> tableau = new Stack<Carta>();
      for (int linha = 0; linha < fileira + 1; linha++) {
        Carta c = baralho.pop();
        c.setVisibilidade(linha == fileira);
        tableau.push(c);
      }
      this.tableaus.add(new Fileira(tableau));
    }
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
    int quantidade = 0;
    Vector<Carta> aMover = tableaus.get(de).getCartas();

    for (int i = aMover.size() - 1; i >= 0; i--) {
      Carta percorrida = aMover.get(i);
      if (percorrida.estaVisivel() && tableaus.get(para).aceitaCarta(percorrida)) {
        quantidade += 1;
      }
    }

    this.tableaus.get(para).empilhar(this.tableaus.get(de).desempilhar(quantidade));
  }

  public void esvaziarDescarte() {
    this.estoque.empilhar(this.descarte.esvaziar());
  }

  public void revelarTopo(int fileira) {
    this.tableaus.get(fileira).virarCartaTopo();
  }

  public void exibir() {
    this.listarCartas("Estoque", this.estoque.getCartas());
    this.listarCartas("Descarte", this.descarte.getCartas());
    Iterator<Fileira> it = this.tableaus.iterator();
    int numFileira = 1, numFundacao = 1;
    while(it.hasNext()) {
      Fileira fileira = it.next();
      this.listarCartas("Fileira " + numFileira, fileira.getCartas());
      numFileira += 1;
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
