package othelloApp.GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import othelloApp.WinScreen;
import othelloGame.Board;

/**
 * Defining a ScoreGUI class to present the score in application.
 */
public class ScoreGUI extends GridPane {

    private Color player1Color;
    private Color player2Color;

    /**
     * The SCoreGUI's constructor.
     * @param player1Color The first player's color.
     * @param player2Color The second player's color.
     */
    public ScoreGUI(Color player1Color, Color player2Color) {
        this.player1Color = player1Color;
        this.player2Color = player2Color;
    }

    /**
     * The method draws the score on the screen.
     * @param board The game board.
     * @param turn The player's turn.
     */
    public void draw(Board board, boolean turn) {
        double padding = this.getWidth() / 10;
        this.setPadding(new Insets(padding, padding, padding, 6));
        this.getChildren().clear();
        final int radius = 7;
        Circle circle1 = new Circle(radius, player1Color);
        Circle circle2 = new Circle(radius, player2Color);
        Color current;
        if (turn) current = player1Color;
        else current = player2Color;
        Circle circleTurn = new Circle(12, current);
        Text whosTurn = new Text("     Turn");
        Text player1 = new Text("    Score: " + board.getXTiles());
        Text player2 = new Text("    Score: " + board.getOTiles());
        player1.setStyle("-fx-font: 16 Courier;");
        player2.setStyle("-fx-font: 16 Courier;");
        whosTurn.setStyle("-fx-font: 20 Courier;");

        this.add(whosTurn, 0, 5);
        this.add(circleTurn,0,5);
        this.add(circle1,0, 7);
        this.add(player1, 0, 7);
        this.add(circle2,0, 10);
        this.add(player2, 0, 10);
    }

    /**
     * The method declares the winner, and displays the winScreen.
     * @param board The game board.
     */
    public void declareWinner(Board board) {
        String win = "";
        Color color = Color.BLACK;
        this.getChildren().clear();
        if (board.getXTiles() > board.getOTiles()) {
            win = "Player 1 Won!";
            color = player1Color;
        }
        if (board.getOTiles() > board.getXTiles()) {
            win = "Player 2 Won!";
            color = player2Color;
        }
        if (board.getXTiles() == board.getOTiles()) win = "  IT'S A TIE!!!";
        WinScreen winScreen = new WinScreen(color);
        winScreen.displayScreen((Stage) getScene().getWindow(), win);
    }
}
