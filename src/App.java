import java.util.Scanner;

import controller.Monitor;
import controller.Tabuleiro;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println('\n');
            Monitor.mostrarMenuPrincipal();
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Digite a fileira de origem e destino da carta.");
                    System.out.println("Separe por vírgula!");
                    System.out.print("ORIGEM, DESTINO: ");
                    String[] positions = sc.nextLine().split(",");
                    try {
                        Tabuleiro.getInstance().moverCarta(
                            Integer.parseInt(positions[0].trim()),
                            Integer.parseInt(positions[1].trim())
                        );
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Entrada inválida!");
                    }
                    break;
                case "2":
                    System.out.println("Digite a fileira que deseja revelar.");
                    String fileira = sc.nextLine();
                    try {
                        Tabuleiro.getInstance().revelarTopo(Integer.parseInt(fileira));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Entrada inválida!");
                    }
                    break;
                case "3":
                    break;
                case "4":
                    Tabuleiro.getInstance().mudarDificuldade();
                    break;
                case "5":
                    Tabuleiro.resetInstance();
                    break;
                case "6":
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            Tabuleiro.getInstance().exibir();
        }
    }
}
