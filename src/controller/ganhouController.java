package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ganhouController {

    @FXML
    private Button botaoRetornar;

    @FXML
    void botaoRetornar(ActionEvent event) throws IOException {
        TabuleiroBigBertha.resetInstance();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Menu");
        botaoRetornar.getScene().getWindow().hide();// ocultando tela antiga.
    }

}
