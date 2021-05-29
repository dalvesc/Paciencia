package controller;

public abstract class Tabuleiro {
    
    public abstract Boolean moverCarta(int de, int para);

    public abstract boolean checarVitoria();
}
