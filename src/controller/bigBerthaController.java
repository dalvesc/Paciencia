package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.scene.control.ListView;
import models.abstracts.Carta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import javafx.geometry.Pos;


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
    private Button botaoRetornar;

    @FXML
    private Label labelTenteNovamente;//label de erro.

    private int movimentoDE = 0;//salva de onde o usuário quer mover a carta.
    private int movimentoPARA = 0;//salva para onde o  usuário quer mover a carta.

    Stack<Stack<Carta>> estoqueL = TabuleiroBigBertha.getInstance().getEstoque();//pegando estoque.
    Stack<Stack<Carta>> fileiras = TabuleiroBigBertha.getInstance().getTableau();//pegando tableau.
    Stack<Stack<Carta>> fundacoes = TabuleiroBigBertha.getInstance().getFundacoes();//pegando fundações.
    Stack<Stack<Carta>> fundacaoEspecial = TabuleiroBigBertha.getInstance().getFundacaoEspecial();//pegando fundação K.

    Stack<Carta> estoqueAUX2 = estoqueL.peek();//salvando a stack de estoque
    Stack<Carta> estoqueAUX = new Stack<Carta>();

    /**
     * Função de inicialização.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(estoqueL);
        System.out.println(estoqueAUX2);

        setFileiras();
        setEstoque();
        corBackgroundLabel();
    }


    /**
     * Cores para fundações.
     */
    private void corBackgroundLabel() {
        var color = "-fx-background-color: white; -fx-border-color: black;";
        fundacao1.setStyle(color);
        fundacao2.setStyle(color);
        fundacao3.setStyle(color);
        fundacao4.setStyle(color);
        fundacao5.setStyle(color);
        fundacao6.setStyle(color);
        fundacao7.setStyle(color);
        fundacao8.setStyle(color);
        fundacaoK.setStyle(color);
        fundacao1.setAlignment(Pos.CENTER);
        fundacao2.setAlignment(Pos.CENTER);
        fundacao3.setAlignment(Pos.CENTER);
        fundacao4.setAlignment(Pos.CENTER);
        fundacao5.setAlignment(Pos.CENTER);
        fundacao6.setAlignment(Pos.CENTER);
        fundacao7.setAlignment(Pos.CENTER);
        fundacao8.setAlignment(Pos.CENTER);
        fundacaoK.setAlignment(Pos.CENTER);
    }

    /**
     * Inserir cartas nas fileiras.
     */
    private void setFileiras() {
        if (!fileiras.empty()) {
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

    /**
     * Inserir cartas nas fundações.
     */
    private void setFundacoes() {
        if (!fundacoes.empty()) {
            if (movimentoPARA == 0) {
                String data = fundacoes.get(0).peek().toString();
                fundacao1.setText(data);
            } else if (movimentoPARA == 1) {
                String data = fundacoes.get(1).peek().toString();
                fundacao2.setText(data);
            } else if (movimentoPARA == 2) {
                String data = fundacoes.get(2).peek().toString();
                System.out.println(data);
                fundacao3.setText(data);
            } else if (movimentoPARA == 3) {
                String data = fundacoes.get(3).peek().toString();
                System.out.println(data);
                fundacao4.setText(data);
            } else if (movimentoPARA == 4) {
                String data = fundacoes.get(4).peek().toString();
                System.out.println(data);
                fundacao5.setText(data);
            } else if (movimentoPARA == 5) {
                String data = fundacoes.get(5).peek().toString();
                System.out.println(data);
                fundacao6.setText(data);
            } else if (movimentoPARA == 6) {
                String data = fundacoes.get(6).peek().toString();
                System.out.println(data);
                fundacao7.setText(data);
            } else if (movimentoPARA == 7) {
                String data = fundacoes.get(7).peek().toString();
                System.out.println(data);
                fundacao8.setText(data);
            }
        }
    }

    /**
     * Inserir cartas na fundação K.
     */
    private void setFundacaoEspecial() {
        if (!fundacaoEspecial.empty()) {
            String data = fundacaoEspecial.pop().peek().toString();
            System.out.println(data);
            fundacaoK.setText(data);
        }
    }

    /**
     * Inserindo cartas iniciais do estoque.
     */
    private void setEstoque() {
        if (!estoqueL.empty()) {
            for (int i = 1; i <= 3; i++) {
                Carta cartaAUX = estoqueAUX2.get(estoqueAUX2.size() - i);
                estoqueAUX.push(cartaAUX);
            }
            ObservableList<Carta> data = FXCollections.observableList(estoqueAUX);
            estoque.setItems(data);
        }

    }

    /**
     * Atualizando cartas do estoque.
     */
    private void atualizaEstoque() {
        estoqueAUX.remove(0);
        if (estoqueAUX.size() < 3 && !estoqueAUX2.isEmpty()) {
            if (estoqueAUX2.size() >= 3) {
                Carta cartaAUX = estoqueAUX2.get(estoqueAUX2.size() - 3);
                estoqueAUX.push(cartaAUX);
            }
        }
        ObservableList<Carta> data = FXCollections.observableList(estoqueAUX);
        estoque.setItems(data);
    }

    /**
     * Quando apertado o botão 'mover' ele chama o método de mover.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void botaoMover(ActionEvent event) throws IOException {
        boolean resp = TabuleiroBigBertha.getInstance().moverCarta(movimentoDE, movimentoPARA);
        System.out.println(resp);
        if (!TabuleiroBigBertha.getInstance().checarVitoria()) {//caso o jogador ganhe.
            if (resp) {//caso o movimento seja possível.
                labelTenteNovamente.setVisible(false);//colocando a label de erro para invisível .

                this.estoqueL = TabuleiroBigBertha.getInstance().getEstoque();//atualiza cartas do estoque.
                this.fileiras = TabuleiroBigBertha.getInstance().getTableau();//atualiza cartas fileiras.
                this.fundacoes = TabuleiroBigBertha.getInstance().getFundacoes();//atualiza cartas fundações.
                this.fundacaoEspecial = TabuleiroBigBertha.getInstance().getFundacaoEspecial();//atualiza cartas fundação K.

                setFileiras();//inserindo as informações nas fileiras.
                if (movimentoDE == 24) {//caso tenha pegado carta do estoque.
                    atualizaEstoque();
                }

                if (movimentoPARA < 8) {//caso tenha movido carta para fundações.
                    setFundacoes();
                } else if (movimentoPARA == 8) {//caso tenha movido carta para fundação K.
                    setFundacaoEspecial();
                }
            } else {
                labelTenteNovamente.setVisible(true);//caso o movimento seja inválido.
            }
        } else {
            //jogador ganhou.
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/ganhou.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            stage.setTitle("Ganhou");
            botaoMover.getScene().getWindow().hide();
        }
    }

    /**
     * Botão para retornar para o menu principal.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void botaoRetornar(ActionEvent event) throws IOException {
        TabuleiroBigBertha.resetInstance();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Menu");
        botaoRetornar.getScene().getWindow().hide();//ocultando tela antiga.
    }


    @FXML
    void estoqueID(ActionEvent event) {
        movimentoDE = 24;
    }

    @FXML
    void fileiraID1(ActionEvent event) {
        movimentoDE = 9;
    }

    @FXML
    void fileiraID2(ActionEvent event) {
        movimentoDE = 10;
    }

    @FXML
    void fileiraID3(ActionEvent event) {
        movimentoDE = 11;
    }

    @FXML
    void fileiraID4(ActionEvent event) {
        movimentoDE = 12;
    }

    @FXML
    void fileiraID5(ActionEvent event) {
        movimentoDE = 13;
    }

    @FXML
    void fileiraID6(ActionEvent event) {
        movimentoDE = 14;
    }

    @FXML
    void fileiraID7(ActionEvent event) {
        movimentoDE = 15;
    }

    @FXML
    void fileiraID8(ActionEvent event) {
        movimentoDE = 16;
    }

    @FXML
    void fileiraID9(ActionEvent event) {
        movimentoDE = 17;
    }

    @FXML
    void fileiraID10(ActionEvent event) {
        movimentoDE = 18;
    }

    @FXML
    void fileiraID11(ActionEvent event) {
        movimentoDE = 19;
    }

    @FXML
    void fileiraID12(ActionEvent event) {
        movimentoDE = 20;
    }

    @FXML
    void fileiraID13(ActionEvent event) {
        movimentoDE = 21;
    }

    @FXML
    void fileiraID14(ActionEvent event) {
        movimentoDE = 22;
    }

    @FXML
    void fileiraID15(ActionEvent event) {
        movimentoDE = 23;
    }

    @FXML
    void fileiraIP1(ActionEvent event) {
        movimentoPARA = 9;
    }

    @FXML
    void fileiraIP2(ActionEvent event) {
        movimentoPARA = 10;
    }

    @FXML
    void fileiraIP3(ActionEvent event) {
        movimentoPARA = 11;
    }

    @FXML
    void fileiraIP4(ActionEvent event) {
        movimentoPARA = 12;
    }

    @FXML
    void fileiraIP5(ActionEvent event) {
        movimentoPARA = 13;
    }

    @FXML
    void fileiraIP6(ActionEvent event) {
        movimentoPARA = 14;
    }

    @FXML
    void fileiraIP7(ActionEvent event) {
        movimentoPARA = 15;
    }

    @FXML
    void fileiraIP8(ActionEvent event) {
        movimentoPARA = 16;
    }

    @FXML
    void fileiraIP9(ActionEvent event) {
        movimentoPARA = 17;
    }

    @FXML
    void fileiraIP10(ActionEvent event) {
        movimentoPARA = 18;
    }

    @FXML
    void fileiraIP11(ActionEvent event) {
        movimentoPARA = 19;
    }

    @FXML
    void fileiraIP12(ActionEvent event) {
        movimentoPARA = 20;
    }

    @FXML
    void fileiraIP13(ActionEvent event) {
        movimentoPARA = 21;
    }

    @FXML
    void fileiraIP14(ActionEvent event) {
        movimentoPARA = 22;
    }

    @FXML
    void fileiraIP15(ActionEvent event) {
        movimentoPARA = 23;
    }

    @FXML
    void fudacaoIP1(ActionEvent event) {
        movimentoPARA = 0;
    }

    @FXML
    void fudacaoIP2(ActionEvent event) {
        movimentoPARA = 1;
    }

    @FXML
    void fudacaoIP3(ActionEvent event) {
        movimentoPARA = 2;
    }

    @FXML
    void fudacaoIP4(ActionEvent event) {
        movimentoPARA = 3;
    }

    @FXML
    void fudacaoIP5(ActionEvent event) {
        movimentoPARA = 4;
    }

    @FXML
    void fudacaoIP6(ActionEvent event) {
        movimentoPARA = 5;
    }

    @FXML
    void fudacaoIP7(ActionEvent event) {
        movimentoPARA = 6;
    }

    @FXML
    void fudacaoIP8(ActionEvent event) {
        movimentoPARA = 7;
    }

    @FXML
    void fudacaoIPK(ActionEvent event) {
        movimentoPARA = 8;
    }
}