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
import javafx.scene.control.MenuItem;



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
        setMenu();
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

    private void setMenu(){
        MenuItem estoque = new MenuItem("ESTOQUE");
        MenuItem fileira1 = new MenuItem("FILEIRA 1");
        MenuItem fileira2 = new MenuItem("FILEIRA 2");
        MenuItem fileira3 = new MenuItem("FILEIRA 3");
        MenuItem fileira4 = new MenuItem("FILEIRA 4");
        MenuItem fileira5 = new MenuItem("FILEIRA 5");
        MenuItem fileira6 = new MenuItem("FILEIRA 6");
        MenuItem fileira7 = new MenuItem("FILEIRA 7");
        MenuItem fileira8 = new MenuItem("FILEIRA 8");
        MenuItem fileira9 = new MenuItem("FILEIRA 9");
        MenuItem fileira10 = new MenuItem("FILEIRA 10");
        MenuItem fileira11 = new MenuItem("FILEIRA 11");
        MenuItem fileira12 = new MenuItem("FILEIRA 12");
        MenuItem fileira13 = new MenuItem("FILEIRA 13");
        MenuItem fileira14 = new MenuItem("FILEIRA 14");
        MenuItem fileira15 = new MenuItem("FILEIRA 15");
        MenuItem fundacao1 = new MenuItem("FUNDAÇAO 1");
        MenuItem fundacao2 = new MenuItem("FUNDAÇAO 2");
        MenuItem fundacao3 = new MenuItem("FUNDAÇAO 3");
        MenuItem fundacao4 = new MenuItem("FUNDAÇAO 4");
        MenuItem fundacao5 = new MenuItem("FUNDAÇAO 5");
        MenuItem fundacao6 = new MenuItem("FUNDAÇAO 6");
        MenuItem fundacao7 = new MenuItem("FUNDAÇAO 7");
        MenuItem fundacao8 = new MenuItem("FUNDAÇAO 8");
        MenuItem fundacaoK = new MenuItem("FUNDAÇAO K");

        deMovimento.getItems().addAll(estoque, fileira1, fileira2, fileira3, fileira4,
        fileira5, fileira6, fileira7, fileira8, fileira9, fileira10, fileira11, fileira12,
        fileira13, fileira14, fileira15);

        paraMovimento.getItems().addAll(fileira1, fileira2, fileira3, fileira4, fileira5,
        fileira6, fileira7, fileira8, fileira9, fileira10, fileira11, fileira12, fileira13,
        fileira14, fileira15, fundacao1, fundacao2, fundacao3, fundacao4, fundacao5, fundacao6, 
        fundacao7, fundacao8, fundacaoK);
        
    }
}

