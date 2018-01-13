package othelloApp;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            primaryStage.setTitle("Othello");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
