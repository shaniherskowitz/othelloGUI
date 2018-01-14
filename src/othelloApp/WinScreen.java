package othelloApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.System.exit;

/**
 * Created by Liora on 13-Jan-18.
 */
public class WinScreen {
    private double angle;
    //private Sleeper sleeper;

    public WinScreen() { this.angle = 0; }

    public void display(Stage stage, String text) {
        Double change = new Double(0.01);
        this.angle += change;
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane, 520, 400);
        scene.setFill(Color.BLACK);
        Color color = Color.rgb(158, 18, 113);
        pane.setStyle("-fx-background-color: rgb(255, 224, 251)");
        //initPane(scene.getRoot());
        for (int i = 0; i < 360; i += 9) {
            Double x = new Double(250 + (170 * (Math.cos(i + this.angle))));
            Double y = new Double(200 + (170 * (Math.sin(i + this.angle))));
            int xp = x.intValue();
            int yp = y.intValue();
            Text star = new Text(xp, yp, "\u2606");
            star.setFont(Font.font ("Arial", 30));
            star.setStyle("-fx-font-weight: bold");
            star.setFill(color);
            pane.getChildren().add(star);
        }
        Text msg = new Text(135, 200, text);
        msg.setFont(Font.font ("Arial", 40));
        msg.setStyle("-fx-font-weight: bold");
        msg.setFill(color);
        pane.getChildren().add(msg);
        stage.setScene(scene);
        stage.show();
    }
    private void initPane(Pane pane) {
        Button quit = new Button("Quit");
        Button mainMenu = new Button("Main Menu");
        quit.setOnAction(event -> { exit(1); });
        final Stage stage = (Stage) pane.getScene().getWindow();
        mainMenu.setOnAction(event -> { loadMainMenu(stage); });
        pane.getChildren().add(quit);
        pane.getChildren().add(mainMenu);
    }

    final public void loadMainMenu(Stage stage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.println("Couldn't load main menu."); }
    }
    public void displayScreen(Stage stage) {
        int frameRate = 60;
        int millisecondsPerFrame = 1000 / frameRate;
        while (true) {
            //long startTime = System.currentTimeMillis();
            double dt = 1 / (double) (frameRate);
            display(stage , "");
            stage.show();
            //this.surface.show(d);
            //long usedTime = System.currentTimeMillis() - startTime;
           // long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
           // if (milliSecondLeftToSleep > 0) { this.sleeper.sleepFor(milliSecondLeftToSleep); }
        }
    }
}
