package othelloApp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import static java.lang.System.exit;

/**
 * Defining a WinScreen class to display the winner at the end of the game.
 */
public class WinScreen {

    private double angle;
    private Color color;

    /**
     * The WinScreen constructor.
     * @param color The star color.
     */
    public WinScreen(Color color) {
        this.angle = 0;
        this.color = color;
    }

    /**
     * The method loads the main menu and displays it.
     * @param stage The stage on which would be displayed.
     */
    private void loadMainMenu(Stage stage) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("files/menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("files/app.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.println("Couldn't load main menu."); }
    }

    /**
     * The method defines the root group and scene of the winScreen.
     * @param stage The stage on which winScreen would be displayed.
     * @param text The text winScreen should display.
     */
    public void displayScreen(Stage stage, String text) {
        stage.setTitle("Animation");
        Group root = new Group();
        Scene scene = new Scene(root, 520, 400, Color.rgb(255, 224, 251));
        scene.getStylesheets().add(getClass().getResource("files/app.css").toExternalForm());
        stage.setScene(scene);
        addStars(scene, text);
        stage.show();
    }

    /**
     * The method adds the stars to animation, and plays it.
     * @param scene The scene the animation will run on.
     * @param text The text the animation should display.
     */
    private void addStars(Scene scene, String text) {
        ArrayList<Text> stars = new ArrayList<>();
        final Group root = (Group) scene.getRoot();
        Text mainStar = new Text(185, 280, "\u2606");
        mainStar.setFont(Font.font("Arial", 160));
        mainStar.setFill(color);
        root.getChildren().add(mainStar);
        addButtons(root, text);
        for (int i = 0; i < 360; i += 9) {
            double xp = 250 + (170 * (Math.cos(angle + i)));
            double yp = 200 + (170 * (Math.sin(angle + i)));
            Text star = new Text(xp, yp, "\u2606");
            star.setFont(Font.font("Arial", 30));
            star.setFill(color);
            stars.add(star);
            root.getChildren().add(star);
        }
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.02),event -> {
                        double change = 0.01;
                        angle += change;
                        for (int i  = 0 ; i < 360; i += 9) {
                            double x = 250 + (170 * (Math.cos(angle + i)));
                            double y = 200 + (170 * (Math.sin(angle + i)));
                            stars.get((i/9)).setX(x);
                            stars.get((i/9)).setY(y);
                        }
        });
        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    /**
     * The method adds buttons and text to root scene animation.
     * @param root The scene's root.
     * @param text The given text.
     */
    private void addButtons(Group root, String text) {
        Text msg = new Text(135, 150, text);
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
