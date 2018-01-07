package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root,400,350);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("FXML Welcome");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        /*GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Othello");
        Label lbl = new Label("Menu:");
        lbl.setFont(new Font("Arial", 30));
        Button btn = new Button("Start game");
        btn.setOnAction(event -> {
            lbl.setText("Button clicked!");
        });
        //VBox root = new VBox();
        //root.getChildren().add(lbl);
        //root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
