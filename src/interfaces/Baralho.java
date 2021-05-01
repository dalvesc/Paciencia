package interfaces;

import java.util.List;

import javax.management.InvalidAttributeValueException;

import models.abstracts.Carta;

public interface Baralho {
  List<Carta> create() throws InvalidAttributeValueException;
}
