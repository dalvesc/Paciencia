package models.enums;

public enum Valor {
  AS(0),
  DOIS(1),
  TRES(2),
  QUATRO(3),
  CINCO(4),
  SEIS(5),
  SETE(6),
  OITO(7),
  NOVE(8),
  DEZ(9),
  VALETE(10),
  RAINHA(11),
  REI(12);

  public int peso;

  Valor(int peso) {
    this.peso = peso;
  }
}
