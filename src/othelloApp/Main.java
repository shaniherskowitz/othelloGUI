package othelloApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Defining the Main class to run the application.
 */
public class Main extends Application {

    /**
     * The method starts the application with the given stage.
     * @param primaryStage The given primary stage.
     * @throws Exception The method throws an exception if could not load the
     * main menu's FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("files/menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            primaryStage.setTitle("Othello");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * The method calls launch to run the application.
     * @param args The main's arguments.
     */
    public static void main(String[] args) { launch(args); }
}
