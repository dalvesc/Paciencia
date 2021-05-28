package controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.partidas.PartidaBigBertha;
import models.estruturas.*;

/**
 * Regras big bertha.
 * 
 * @author Adlla Katarine e Daniel Alves
 */
public class TabuleiroBigBertha {
    private static TabuleiroBigBertha tabuleiroBB = null;
    private Vector<Estrutura> elementosPartida;
    private static final int qtdCartasEstoque = 1;

    private TabuleiroBigBertha() throws InvalidAttributeValueException {
        this.elementosPartida = new PartidaBigBertha().create();
    }

    public static TabuleiroBigBertha getInstance() {
        if (tabuleiroBB == null) {
            try {
                tabuleiroBB = new TabuleiroBigBertha();
            } catch (InvalidAttributeValueException e) {
                e.printStackTrace();
            }
        }
        return tabuleiroBB;
    }

    public static void resetInstance() {
        try {
            tabuleiroBB = new TabuleiroBigBertha();
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Método que move a(s) carta(s) de uma estrutura para outra de acordo com as
     * regras do Big Bertha.
     * 
     * @param de   estrtura de origem.
     * @param para estrutura de destino.
     */
    public Boolean moverCarta(int de, int para) {
        Estrutura origem = this.elementosPartida.get(de);
        Estrutura destino = this.elementosPartida.get(para);

        if (origem instanceof Estoque) {
            if (destino instanceof Descarte) {
                this.elementosPartida.get(para)
                        .empilhar(((Estoque) this.elementosPartida.get(de)).desempilhar(qtdCartasEstoque));
            } else if (destino instanceof Tableau) {
                if (((Tableau) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Estoque) this.elementosPartida.get(de)).desempilhar());
                }else{
                    return false;
                }
            } else if (destino instanceof Fundacao) {
                if (((Fundacao) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());//erro aqui na hora do estoque
                }else{
                    return false;
                }
            } else if (destino instanceof FundacaoEspecial) {
                if (((FundacaoEspecial) destino).aceitaCarta(origem.verCartaTopo())) {
                    if(origem instanceof Tableau){
                    this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
                    }else{
                        this.elementosPartida.get(para).empilhar(((Estoque) this.elementosPartida.get(de)).desempilhar());
                    }
                }else{
                    return false;
                }
            }
        } else if (origem instanceof Tableau) {
            if (destino instanceof Tableau) {
                /*int quantidadeRejeitada = 0;
                int quantidadeDesempilhar = 0;
                Vector<Carta> aMover = origem.getCartas();
                for (int i = 0; i < aMover.size(); i++) {
                    Carta percorrida = aMover.get(i);
                    if (((Tableau) destino).aceitaCarta_OutraRegra(percorrida)) {
                        break;
                    } else {
                        quantidadeRejeitada += 1; //me explicar isso erro
                    }
                }
                quantidadeDesempilhar = aMover.size() - quantidadeRejeitada;*/
                Vector<Carta> aMover = origem.getCartas();
                int carta = 0;
                boolean correto = true;
                for (int i = carta; i >= 0; i--) {
                    if(i > 0){
                        if(!compararCorEValor(aMover.get(i), aMover.get(i-1))){
                            correto = false;
                        }
                    }
                }
                if(correto){
                    if(((Tableau) destino).aceitaCarta_OutraRegra(aMover.get(aMover.size()))){
                        Stack<Carta> desempilhado = this.elementosPartida.get(de).desempilhar(carta);
                        Collections.reverse(desempilhado);
                        this.elementosPartida.get(para).empilhar(desempilhado);
                    }
                }
            } else if (destino instanceof Fundacao) {
                if (((Fundacao) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
                }else{
                    return false;
                }
            } else if (destino instanceof FundacaoEspecial) {
                if (((FundacaoEspecial) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private boolean compararCorEValor(Carta cartaAtual, Carta cartaAnterior){
        boolean temValorMenor = cartaAtual.getValor().peso == cartaAnterior.getValor().peso - 1;
    
        boolean cartaAtualIsVermelha = cartaAtual.getNomeNaipe().equals('♥') || cartaAtual.getNomeNaipe().equals('♦');
        boolean cartaAnteriorIsVermelha = cartaAnterior.getNomeNaipe().equals('♥') || cartaAnterior.getNomeNaipe().equals('♦');
        //comparação cores
        return temValorMenor && Boolean.logicalXor(cartaAnteriorIsVermelha, cartaAtualIsVermelha);
    }

    /**
     * 
     * Método que revela a penultima carta do estoque, ficando assim as três últimas
     * cartas visiveis.
     */
    public void revelarPenultimaCarta() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        boolean encontrou = false; // variável se torna true quando é encontrada a estrutura desejava e assim
                                   // finaliza a estrutura de repetição.
        while (it.hasNext() || encontrou) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Estoque) {
                estrutura.getCartas().get(estrutura.getCartas().size() - 4).setVisibilidade(true);
                encontrou = true;
            }
        }
    }

    /**
     * Método que verifica se o jogo foi ganho, conferindo se toda as pilhas de
     * fundações estão completas.
     * 
     * @return boolean - true se todas as pilhas estiverem completas.
     */
    public boolean checarVitoria() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Fundacao) {
                if (estrutura.getCartas().size() < 12) {
                    return false;
                }
            } else if (estrutura instanceof FundacaoEspecial) {
                if (estrutura.getCartas().size() < 8) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Retorna as fileiras do jogo da 15 para 1.
     * 
     * @return uma pilha de pilhas contendo as filheiras
     */
    public Stack<Stack<Carta>> getTableau() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        Stack<Stack<Carta>> tableau = new Stack<Stack<Carta>>();
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Tableau) {
                tableau.push(estrutura.getCartas());
            }
        }
        return tableau;
    }

    
    public Stack<Stack<Carta>> getEstoque() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        Stack<Stack<Carta>> estoque = new Stack<Stack<Carta>>();
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Estoque) {
                estoque.push(estrutura.getCartas());
                return estoque;
            }
        } return null;
    }

    public Stack<Stack<Carta>> getFundacoes() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        Stack<Stack<Carta>> fundacoes = new Stack<Stack<Carta>>();
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Fundacao) {
                fundacoes.push(estrutura.getCartas());
            }
        }
        return fundacoes;
    }

    public Stack<Stack<Carta>> getFundacaoEspecial() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        Stack<Stack<Carta>> fundacaoEspecial = new Stack<Stack<Carta>>();
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof FundacaoEspecial) {
                fundacaoEspecial.push(estrutura.getCartas());
                return fundacaoEspecial;
            }
        }
        return null;
    }
}