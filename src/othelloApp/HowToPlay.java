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
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Defining a HowToPlay screen class.
 */
public class HowToPlay implements Initializable {

    @FXML
    private Button startGameButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private AnchorPane root;

    /**
     * The HowToPlay default constructor.
     */
    public HowToPlay() {}

    /**
     * The method initializes the HowToPlay class as the controller to HowToPlay FXML doc.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getInstruction();
    }

    /**
     * The method reads the game instructions from a documents and prints it to the screen.
     */
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

    /**
     * The method loads the main menu screen and displays it.
     */
    @FXML
    protected void loadMainMenu() {
        try {
            Stage stage = (Stage) this.mainMenuButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("files/menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("files/app.css").toExternalForm());
            stage.setTitle("Othello");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * The method loads the othello game, and starts the game/
     */
    @FXML
    protected void startGame() {
        try {
            Stage stage = (Stage) this.startGameButton.getScene().getWindow();
            AnchorPane root = FXMLLoader.load(getClass().getResource("files/board.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("files/app.css").toExternalForm());
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
