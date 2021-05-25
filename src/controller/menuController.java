package controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import views.MenuPaciencia;


/**
 * Controller para funções do menu principal.
 * 
 * @author Adlla Katarine e Daniel Alves
 */
public class menuController {

    @FXML
    private Button paciencia;//botão para opção paciência.

    @FXML
    private Button bigbertha;//botão para opção big bertha.


    /**
     * Ações para o botão Big Bertha.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void escolhaBigBertha(ActionEvent event) throws IOException {
        //abrindo nova tela para inicialização do jogo paciência.
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/bigbertha.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Big Bertha");
        bigbertha.getScene().getWindow().hide();//ocultando tela antiga.
    }

    /**
     * Ações para o botão Paciência.
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void escolhaPaciencia(ActionEvent event) throws Exception {
        //abrindo nova tela para inicialização do jogo paciência.
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/escolhaPaciencia.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Paciência");
        paciencia.getScene().getWindow().hide();//ocultando tela antiga.

        //nova thread para iniciar o paciência.
        new Thread(){
            @Override
            public void run() {
                try {
                    MenuPaciencia.iniciaPaciencia();//iniciando jogo paciência.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
