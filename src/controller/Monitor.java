package controller;

public class Monitor {
  public static void mostrarMenuPrincipal() {
    System.out.println(
      "1 - Mover Carta \n" +
      "2 - Revelar carta do topo da fileira \n" +
      "3 - Exibir Jogo \n" +
      "4 - Alterar quantidade de cartas a serem viradas do estoque \n" +
      "5 - Reiniciar \n" +
      "6 - Encerrar Partida \n"
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
