package othelloApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Liora on 13-Jan-18.
 */
public class WinScreen {
    private double angle;
    private int countr = 100000;
    //private Sleeper sleeper;

    /**
     * The constructor of HighScoresAnimation.
     */
    public WinScreen() {
        this.angle = 0;
    }


    /**
     * This method is for draws the high scores table.
     *
     * @param d  DrawSurface parameter
     * @param dt the dt parameter of speed
     */
    public void doOneFrame(Pane d, double dt) {
        //d.setFill(Color.BLACK);
        //d.setColor(Color.BLACK);
        //d.fillRectangle(0, 0 , 800, 600);
        //d.setColor(new Color(218, 165, 32));
        Text text = new Text(200, 160, "Player 1 Won!!");
        text.setFill(Color.rgb(255, 102, 178));
        //d.setColor(Color.rgb(255, 102, 178));
        //d.drawText(220, 160, "HIGH SCORES", 58);
        //java.util.List<ScoreInfo> table = this.highScores.getHighScores();
        for (int i = 0; i < 360; i += 9) {
            Double x = new Double(400 + (300 * (Math.cos(i + this.angle))));
            Double y = new Double(310 + (270 * (Math.sin(i + this.angle))));
            int xp = x.intValue();
            int yp = y.intValue();
            Text star = new Text(xp, yp, "\u2606");
            d.getChildren().add(star);
            //d.drawText(xp, yp, "\u2606", 50);
        }
        Double change = new Double(0.01);
        this.angle += change;
    }

    public void displayScreen(Stage stage) {
        int frameRate = 60;
        int millisecondsPerFrame = 1000 / frameRate;
        while (countr > 0) {
            //long startTime = System.currentTimeMillis();
            double dt = 1 / (double) (frameRate);

            Pane pane = new GridPane();
            //Scene scene = new Scene(settingsPage, 520, 400);
            Scene scene = new Scene(pane, 520, 400);
            scene.setFill(Color.BLACK);
            doOneFrame(pane, dt);
            stage.setScene(scene);
            countr--;
            //this.surface.show(d);
            //long usedTime = System.currentTimeMillis() - startTime;
           // long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
           // if (milliSecondLeftToSleep > 0) { this.sleeper.sleepFor(milliSecondLeftToSleep); }
        }
    }
}
