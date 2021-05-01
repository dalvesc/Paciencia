package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import models.BaralhoPaciencia;
import models.Copas;
import models.Espadas;
import models.Fileira;
import models.Ouro;
import models.Paus;
import models.abstracts.Carta;

public class Tabuleiro {
  private static Tabuleiro tabuleiro = null;
  private List<Fileira> tableaus = new ArrayList<Fileira>(7);
  private Stack<Carta> estoque = new Stack<Carta>();
  private Stack<Carta> descarte = new Stack<Carta>();

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
    Iterator<Carta> itEstoque = baralho.iterator();
    while(itEstoque.hasNext()) {
      this.estoque.push(itEstoque.next());
    }
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
    if (this.estoque.isEmpty()) {
      System.out.println("Estoque vazio");
      return;
    }
    this.descarte.push(this.estoque.pop());
  }

  public void moverCarta(int de, int para) throws InvalidAttributeValueException {
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

  public void revelarTopo(int fileira) {
    this.tableaus.get(fileira).virarCartaTopo();
  }

  public void exibir() {
    this.listarCartas("Estoque", this.estoque);
    this.listarCartas("Descarte", this.descarte);
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

  private boolean podeMover(Carta origem, Carta destino) {
    boolean aceitaValor = destino.getValor().peso - 1 == origem.getValor().peso;
    System.out.println(destino.getValor().peso - 1);
    boolean origemEhVermelha = origem instanceof Copas || origem instanceof Ouro;
    boolean destinoEhVermelho = origem instanceof Espadas || origem instanceof Paus;
    return aceitaValor && (origemEhVermelha != destinoEhVermelho);
  }
}
