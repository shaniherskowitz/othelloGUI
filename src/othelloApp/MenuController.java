package othelloApp;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class MenuController {
    private Stage stage;

    @FXML
    private Button settingsButton;
    @FXML
    private Button startGameButton;

    private Stage getStage(Button button) {
        return (Stage) button.getParent().getScene().getWindow();
    }

        @FXML
    protected void settings() {
        try {
            stage = getStage(settingsButton);
            GridPane settingsPage = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene scene = new Scene(settingsPage, 520, 400);
            scene.getStylesheets().add(getClass().getResource("settings.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.print("Exception in loading settings fxml"); }
    }

    @FXML
    protected void startGame() {
        try {
            stage = getStage(startGameButton);
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("board.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
    @FXML
    protected void howToPlay() {
        String textFile = getInstruction();
        Text text = new Text(textFile);
        TextFlow textFlow = new TextFlow(text);
        Scene scene = new Scene(textFlow, 520, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    private String getInstruction() {
        String fileName = "othelloApp/howToPlay.txt";
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String curLine = bufferedReader.readLine();
            while (curLine != null){
                sb.append(curLine);
                curLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) { System.out.println("Unable to open how to play");
        } catch (IOException ex) { System.out.println("Error reading how to play"); }
        return sb.toString();
    }

    @FXML
    protected void quit() { exit(1); }
}