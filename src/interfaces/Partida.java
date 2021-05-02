package interfaces;

import java.util.Vector;

import javax.management.InvalidAttributeValueException;

import models.abstracts.Estrutura;

public interface Partida {
  Vector<Estrutura> create() throws InvalidAttributeValueException;
}
