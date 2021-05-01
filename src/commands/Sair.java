package commands;

import controller.Monitor;
import interfaces.Command;

public class Sair implements Command{

  @Override
  public void execute() {
    Monitor.sair();
    System.exit(0); 
  }
  
}
