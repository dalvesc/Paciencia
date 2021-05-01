package commands;

import javax.management.InvalidAttributeValueException;

import controller.Tabuleiro;
import interfaces.Command;

public class MoverCarta implements Command{
  private Tabuleiro tabuleiro;
  private int de, para;

  public MoverCarta(int de, int para) throws InvalidAttributeValueException {
    if (!jogadaValida(de, para)) {
      throw new InvalidAttributeValueException();
    }
    this.de = de;
    this.para = para;
    System.out.println(de + " " + para);
    this.tabuleiro = Tabuleiro.getInstance();
  }

  @Override
  public void execute() {
    this.tabuleiro.moverCarta(de, para);
  }

  private boolean jogadaValida(int posicao1, int posicao2) {
    return (posicao1 >= 0 && posicao1 < 8) && (posicao2 >= 0 && posicao2 < 8);
  }
}
