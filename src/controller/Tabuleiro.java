package controller;

/**
 * Classe abstrata com os métodos semelhantes à outros tabuleiros.
 * 
 */

public abstract class Tabuleiro {
    
    public abstract Boolean moverCarta(int de, int para);

    public abstract boolean checarVitoria();
}
