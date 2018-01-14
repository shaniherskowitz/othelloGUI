package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

/**
 * Created by Liora on 14-Jan-18.
 */
public class HowToPlay implements Initializable {

    @FXML
    private Button startGameButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private AnchorPane root;

    public HowToPlay() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getInstruction();
    }

    private void getInstruction() {
        String fileName = "othelloApp/howToPlay.txt";
        double height = 100;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String curLine = bufferedReader.readLine();
            while (curLine != null){
                Text text = new Text(curLine);
                text.setFill(Color.rgb(158, 18, 113));
                text.setFont(Font.font(14));
                text.setX(50);
                text.setY(height);
                root.getChildren().add(text);
                curLine = bufferedReader.readLine();
                height += 20;
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) { System.out.println("Unable to open how to play");
        } catch (IOException ex) { System.out.println("Error reading how to play"); }
    }

    @FXML
    protected void quit() { exit(1); }

    @FXML
    public void loadMainMenu() {
        try {
            Stage stage = (Stage) this.mainMenuButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Othello");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void startGame() {
        try {
            Stage stage = (Stage) this.startGameButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("board.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
