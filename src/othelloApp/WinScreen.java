package othelloApp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * Created by Liora on 13-Jan-18.
 */
public class WinScreen {
    //private Button mainMenuButton;
    //private Button quitButton;
    private double angle;

    public WinScreen() { this.angle = 0; }

    final public void loadMainMenu(Stage stage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.println("Couldn't load main menu."); }
    }

    public void displayScreen(Stage stage, String text) {
        stage.setTitle("Animation");
        Group root = new Group();
        Scene scene = new Scene(root, 520, 400, Color.rgb(255, 224, 251));
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setScene(scene);
        addStars(scene, text);
        stage.show();
    }

    private void addStars(Scene scene, String text) {
        ArrayList<Text> stars = new ArrayList<>();
        final Group root = (Group) scene.getRoot();
        addButtons(root, text);
        for (int i = 0; i < 360; i += 9) {
            Double x = new Double(250 + (170 * (Math.cos(angle + i))));
            Double y = new Double(200 + (170 * (Math.sin(angle + i))));
            int xp = x.intValue();
            int yp = y.intValue();
            Text star = new Text(xp, yp, "\u2606");
            star.setFont(Font.font("Arial", 30));
            stars.add(star);
            root.getChildren().add(star);
        }
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.02),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        Double change = new Double(0.01);
                        angle += change;
                        for (int i  = 0 ; i < 360; i += 9) {
                            Double x = new Double(250 + (170 * (Math.cos(angle + i))));
                            Double y = new Double(200 + (170 * (Math.sin(angle + i))));
                            stars.get((i/9)).setX(x);
                            stars.get((i/9)).setY(y);
                        }
                    }
                });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    private void addButtons(Group root, String text) {
        Text msg = new Text(135, 200, text);
        msg.setFont(Font.font("Arial", 40));
        msg.setStyle("-fx-font-weight: bold");
        Color color = Color.rgb(158, 18, 113);
        msg.setFill(color);
        root.getChildren().add(msg);
        Button quitButton = new Button("Quit");
        quitButton.setLayoutX(480);
        quitButton.setLayoutY(375);
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setLayoutX(0);
        mainMenuButton.setLayoutY(375);
        quitButton.setOnAction(event -> { exit(1); });
        final Stage stage = (Stage) root.getScene().getWindow();
        mainMenuButton.setOnAction(event -> { loadMainMenu(stage); });
        root.getChildren().add(mainMenuButton);
        root.getChildren().add(quitButton);
    }
}
