package controller;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.scene.control.ListView;
import models.abstracts.Carta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;


/**
 * Controller para funções do jogo big bertha.
 * 
 * @author Adlla Katarine e Daniel Alves
 */
public class bigBerthaController implements Initializable {

    @FXML
    private ListView<Carta> estoque;

    @FXML
    private ListView<Carta> fileira6;

    @FXML
    private ListView<Carta> fileira7;

    @FXML
    private ListView<Carta> fileira8;

    @FXML
    private ListView<Carta> fileira9;

    @FXML
    private ListView<Carta> fileira10;

    @FXML
    private ListView<Carta> fileira11;

    @FXML
    private ListView<Carta> fileira12;

    @FXML
    private ListView<Carta> fileira13;

    @FXML
    private ListView<Carta> fileira14;

    @FXML
    private ListView<Carta> fileira15;

    @FXML
    private ListView<Carta> fileira5;

    @FXML
    private ListView<Carta> fileira4;

    @FXML
    private ListView<Carta> fileira3;

    @FXML
    private ListView<Carta> fileira2;

    @FXML
    private ListView<Carta> fileira1;

    @FXML
    private Label fundacao1;

    @FXML
    private Label fundacao2;

    @FXML
    private Label fundacao3;

    @FXML
    private Label fundacao4;

    @FXML
    private Label fundacao5;

    @FXML
    private Label fundacao6;

    @FXML
    private Label fundacao7;

    @FXML
    private Label fundacao8;

    @FXML
    private Label fundacaoK;

    @FXML
    private MenuButton deMovimento;

    @FXML
    private MenuButton paraMovimento;

    @FXML
    private Button botaoMover;

    @FXML
    private Label labelTenteNovamente;
    

    Stack<Stack<Carta>> estoqueL = TabuleiroBigBertha.getInstance().getEstoque();
    Stack<Stack<Carta>> fileiras = TabuleiroBigBertha.getInstance().getTableau();
    Stack<Stack<Carta>> fundacoes = TabuleiroBigBertha.getInstance().getFundacoes();
    Stack<Stack<Carta>> fundacaoEspecial = TabuleiroBigBertha.getInstance().getFundacaoEspecial();

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        setFileiras();
    }

    private void setFileiras(){
        if(!fileiras.empty()){
            ObservableList<Carta> data = FXCollections.observableList(fileiras.pop());
            fileira15.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira14.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira13.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira12.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira11.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira10.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira9.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira8.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira7.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira6.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira5.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira4.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira3.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira2.setItems(data);
            data = FXCollections.observableList(fileiras.pop());
            fileira1.setItems(data); 
        }

    }
}

