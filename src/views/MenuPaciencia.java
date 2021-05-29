package views;
import java.util.Scanner;
import controller.TabuleiroPaciencia;


public class MenuPaciencia{

    private MenuPaciencia(){}

    public static void iniciaPaciencia() throws Exception{
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println('\n');   
            mostrarMenuPrincipal();
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Digite a fileira de origem e destino da carta.");
                    System.out.println("Separe por vírgula!");
                    System.out.print("ORIGEM, DESTINO: ");
                    String[] positions = sc.nextLine().split(",");
                    try {
                        TabuleiroPaciencia.getInstance().moverCarta(
                            Integer.parseInt(positions[0].trim()),
                            Integer.parseInt(positions[1].trim())
                        );
                        if (TabuleiroPaciencia.getInstance().checarVitoria()) {
                            System.out.println("Parabéns!! Você venceu!");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Entrada inválida!");
                    }
                    break;
                case "2":
                    System.out.println("Digite a fileira que deseja revelar.");
                    String fileira = sc.nextLine();
                    try {
                        TabuleiroPaciencia.getInstance().revelarTopo(Integer.parseInt(fileira));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Entrada inválida!");
                    }
                    break;
                case "3":
                    break;
                case "4":
                TabuleiroPaciencia.getInstance().mudarDificuldade();
                    break;
                case "5":
                TabuleiroPaciencia.resetInstance();
                    break;
                case "6":
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            TabuleiroPaciencia.getInstance().exibir();
        }
    }

    private static void mostrarMenuPrincipal() {
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
}
