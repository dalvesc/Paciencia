package controller;

import models.abstracts.Carta;

public class Monitor {
  public static void mostrarMenuPrincipal() {
    System.out.println(
      "1 - Mover Carta \n" +
      "2 - Pegar do Estoque \n" +
      "3 - Virar Topo da Fileira \n" +
      "4 - Exibir Jogo \n" +
      "5 - Alterar quantidade de cartas a serem viradas do estoque \n" +
      "6 - Reiniciar \n" +
      "7 - Encerrar Partida \n"
    );
    System.out.print("Escolha uma opção: ");
  }
  public static void parabenizar() {
    System.out.println("Parabéns, você venceu");
  }

  public static void sair() {
    System.out.println("Partida Encerrada.\nSaindo...");
  }
}
