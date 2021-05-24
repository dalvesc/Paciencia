package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class menuController {

    @FXML
    private Button paciencia;

    @FXML
    private Button bigbertha;

    @FXML
    private ImageView carta1;

    @FXML
    private ImageView carta2;

    @FXML
    private ImageView carta3;

    @FXML
    private ImageView carta4;

    @FXML
    void escolhaBigBertha(ActionEvent event) {

    }

    @FXML
    void escolhaPaciencia(ActionEvent event) {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/escolhaPaciencia.fxml"));
       
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Paciência");
        MenuPaciencia.iniciaPaciencia();
    }

}
