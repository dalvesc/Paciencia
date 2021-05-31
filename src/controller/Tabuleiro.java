package controller;


/**
 * Classe abstrata com os métodos semelhantes à outros tabuleiros.
 * 
 * @author Adlla Katarine e Daniel Alves
 */
public abstract class Tabuleiro {
    
    public abstract Boolean moverCarta(int de, int para);

    public abstract boolean checarVitoria();
}
