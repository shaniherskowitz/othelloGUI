package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static java.lang.System.exit;

/**
 * Defining a MenuController class to control the main menu.
 */
public class MenuController {

    @FXML
    private Button settingsButton;
    @FXML
    private Button startGameButton;
    @FXML
    private Button howToPlayButton;

    /**
     * The method returns the given button's stage.
     * @param button The given button.
     * @return The stage.
     */
    private Stage getStage(Button button) {
        return (Stage) button.getParent().getScene().getWindow();
    }

    /**
     * The method loads the settings screen.
     */
    @FXML
    protected void settings() {
        try {
            Stage stage = getStage(settingsButton);
            GridPane settingsPage = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene scene = new Scene(settingsPage, 520, 400);
            scene.getStylesheets().add(getClass().getResource("settings.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.print("Exception in loading settings fxml"); }
    }

    /**
     * The method loads the game screen, and str=arts the game.
     */
    @FXML
    protected void startGame() {
        try {
            Stage stage = getStage(startGameButton);
            AnchorPane root = FXMLLoader.load(getClass().getResource("board.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * The method displays the how to play screen.
     */
    @FXML
    protected void howToPlay() {
        try {
            Stage stage = getStage(howToPlayButton);
            AnchorPane root = FXMLLoader.load(getClass().getResource("howToPlay.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("How To Play");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    protected void quit() { exit(1); }
}
