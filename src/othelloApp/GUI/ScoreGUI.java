package othelloApp.GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import othelloGame.Board;


public class ScoreGUI extends GridPane {
    private Color player1Color;
    private Color player2Color;


    public ScoreGUI(Color player1Color, Color player2Color) {
        this.player1Color = player1Color;
        this.player2Color = player2Color;

    }

    public void draw(Board board, boolean turn) {
        this.setVgap(10);
        this.setPadding(new Insets(10, 10, 10, 6));
        this.getChildren().clear();
        Circle circle1 = new Circle(0, 0 , 7, player1Color);
        Circle circle2 = new Circle(0, 0 , 7, player2Color);
        Color current;
        if (turn) current = player1Color;
        else current = player2Color;
        Circle circleTurn = new Circle(0, 0 , 12, current);
        Text whosTurn = new Text("   Turn");
        Text player1 = new Text("  Score: " + board.getXTiles());

        Text player2 = new Text("  Score: " + board.getOTiles());
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

    public void declareWinner(Board board) {
        Text winner;
        this.getChildren().clear();
        if (board.getXTiles() > board.getOTiles()) {
            winner = new Text("Winner: " + 1);
        } else if(board.getXTiles() < board.getOTiles()) {
            winner = new Text("Winner: " + 2);
        } else {
            winner = new Text("Tie!! ");
        }
        winner.setStyle("-fx-font: 20 Courier;");
        this.add(winner, 0, 5);
    }


}
