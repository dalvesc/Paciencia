import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class App extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/menu.fxml"));
        Parent root = fxmlLoader.load();
        Scene menu = new Scene(root);

        primaryStage.setTitle("Menu Principal");
        primaryStage.setScene(menu);
        primaryStage.show();
    }

    
}
