package controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import models.abstracts.Carta;
import models.abstracts.Estrutura;
import models.enums.Valor;
import models.partidas.PartidaBigBertha;
import models.estruturas.*;

public class TabuleiroBigBertha{
    private static TabuleiroBigBertha tabuleiroBB = null;
    private Vector<Estrututa> elementosPartida;

    private TabuleiroBigBertha () throws InvalidAttributeValueException {
        this.elementosPartida = new PartidaBigBertha().create();
    }

    public static TabuleiroBigBertha getInstance() {
        if (tabuleiroBB == null){
            try {
                tabuleiroBB = new TabuleiroBigBertha();
            } catch (InvalidAttributeValueException e){
                e.printStackTrace();
            }
        }
        return tabuleiroBB;
    }
    
    public static void resetInstance() {
        try {
            tabuleiroBB = new TabuleiroBigBertha();
        } catch (InvalidAttributeValueException e){
            e.printStackTrace();
        }
    }
}