package controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import models.enums.Valor;
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
public class TabuleiroBigBertha extends Tabuleiro{
    private static TabuleiroBigBertha tabuleiroBB = null;
    private Vector<Estrutura> elementosPartida;
    private static final int qtdCartasEstoque = 1;

    private TabuleiroBigBertha() throws InvalidAttributeValueException {
        this.elementosPartida = new PartidaBigBertha().create();
    }

    /**
     * Retorna o tabuleiro do jogo.
     * 
     * @return instância do tabuleiro.
     */
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

    /**
     * Reseta o tabuleiro do jogo.
     */
    public static void resetInstance() {
        try {
            tabuleiroBB = new TabuleiroBigBertha();
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que move a(s) carta(s) de uma estrutura para outra de acordo com as
     * regras do Big Bertha.
     * 
     * @param de   estrutura de origem.
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
                } else {
                    return false;
                }
            } else if (destino instanceof Fundacao) {
                if (((Fundacao) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Estoque) this.elementosPartida.get(de)).desempilhar());// erro
                } else {
                    return false;
                }
            } else if (destino instanceof FundacaoEspecial) {
                if (((FundacaoEspecial) destino).aceitaCarta(origem.verCartaTopo())) {
                    if (origem instanceof Tableau) {
                        this.elementosPartida.get(para)
                                .empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
                    } else {
                        this.elementosPartida.get(para)
                                .empilhar(((Estoque) this.elementosPartida.get(de)).desempilhar());
                    }
                } else {
                    return false;
                }
            }
        } else if (origem instanceof Tableau) {
            if (destino instanceof Tableau) {
                int quantidadeDesempilhar = 0;
                int qualCarta = 0;

                Vector<Carta> aMover = origem.getCartas();
                for (int i = 0; i < aMover.size(); i++) {
                    Carta percorrida = aMover.get(i);
                    if (i < aMover.size() - 1) {

                        if (compararCorEValor(percorrida, aMover.get(i + 1))) {
                            qualCarta++;
                        } else {
                            qualCarta = 0;
                        }
                    }
                }

                quantidadeDesempilhar = aMover.size() - ((aMover.size() - 1) - qualCarta);
                if (((Tableau) destino).aceitaCarta_OutraRegra(aMover.get((aMover.size() - 1) - qualCarta))) {
                    Stack<Carta> desempilhado = this.elementosPartida.get(de).desempilhar(quantidadeDesempilhar);
                    Collections.reverse(desempilhado);
                    this.elementosPartida.get(para).empilhar(desempilhado);
                } else {
                    return false;
                }
            } else if (destino instanceof Fundacao) {
                if (((Fundacao) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
                } else {
                    return false;
                }
            } else if (destino instanceof FundacaoEspecial) {
                if (((FundacaoEspecial) destino).aceitaCarta(origem.verCartaTopo())) {
                    this.elementosPartida.get(para).empilhar(((Tableau) this.elementosPartida.get(de)).desempilhar());
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Compara cor e valor das cartas para verificar se é possível o empilhamento
     * nas fileiras.
     * 
     * @param cartaAtual
     * @param cartaPosterior
     * @return true caso seja possível.
     */
    private boolean compararCorEValor(Carta cartaAtual, Carta cartaPosterior) {
        boolean temValorMenor = cartaAtual.getValor().peso == cartaPosterior.getValor().peso + 1;

        boolean cartaAtualIsVermelha = cartaAtual.getNomeNaipe().equals("♥") || cartaAtual.getNomeNaipe().equals("♦");
        boolean cartaPosteriorIsVermelha = cartaPosterior.getNomeNaipe().equals("♥")
                || cartaPosterior.getNomeNaipe().equals("♦");
        // comparação cores
        return temValorMenor && Boolean.logicalXor(cartaPosteriorIsVermelha, cartaAtualIsVermelha);
    }

    /**
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
        int fundacoesCompletas = 0;
        int fundacaoEspecialCompleta = 0;
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Fundacao) {
                Stack<Carta> cartas = ((Fundacao) estrutura).getCartas();
                if (cartas.firstElement().getValor() == Valor.AS && cartas.lastElement().getValor() == Valor.RAINHA) {
                    fundacoesCompletas += 1;
                }
            } else if (estrutura instanceof FundacaoEspecial) {
                Stack<Carta> cartas = ((FundacaoEspecial) estrutura).getCartas();
                if (cartas.size() == 8) {
                    fundacaoEspecialCompleta = 1;
                }
            }
        }
        return fundacoesCompletas == 8 && fundacaoEspecialCompleta == 1;
    }

    /**
     * Retorna as fileiras do jogo.
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

    /**
     * Retorna estoque do jogo.
     * 
     * @return estoque
     */
    public Stack<Stack<Carta>> getEstoque() {
        Iterator<Estrutura> it = this.elementosPartida.iterator();
        Stack<Stack<Carta>> estoque = new Stack<Stack<Carta>>();
        while (it.hasNext()) {
            Estrutura estrutura = it.next();
            if (estrutura instanceof Estoque) {
                estoque.push(estrutura.getCartas());
                return estoque;
            }
        }
        return null;
    }

    /**
     * Retorna fundações do jogo.
     * 
     * @return fundações
     */
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

    /**
     * Retorna cartas contidas na fundação K.
     * 
     * @return fundação K
     */
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