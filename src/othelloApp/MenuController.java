package othelloApp;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    protected void settings() {
        try {
            Stage stage = new Stage();
            GridPane settingsPage = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene scene = new Scene(settingsPage, 520, 400);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.print("Exception in loading settings fxml"); }
    }
    @FXML
    protected void startGame() {
        try {
            Stage stage = new Stage();
            HBox root = (HBox) FXMLLoader.load(getClass().getResource("board.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Game");
            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
