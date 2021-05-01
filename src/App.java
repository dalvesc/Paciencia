import java.util.Scanner;

import commands.MoverCarta;
import commands.Sair;
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
                        new MoverCarta(
                            Integer.parseInt(positions[0].trim()) - 1, 
                            Integer.parseInt(positions[1].trim()) - 1
                        ).execute();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Entrada inválida!");
                    }
                    break;
                case "2":
                    break;
                case "3":
                    System.out.println("Digite a fileira que deseja revelar.");
                    String fileira = sc.nextLine();
                    Tabuleiro.getInstance().revelarTopo(Integer.parseInt(fileira) - 1);
                    break;
                case "4":;
                    Tabuleiro.getInstance().exibir();
                    break;
                case "6":
                    Tabuleiro.resetInstance();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            Tabuleiro.getInstance().exibir();
        }
    }
}
