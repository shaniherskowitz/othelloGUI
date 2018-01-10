package othelloApp.GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
        this.getChildren().clear();
        int current;
        if (turn) current = 1;
        else current = 2;
        Text whosTurn = new Text("Players Turn: " + current);
        Text player1 = new Text("1 Score: " + board.getXTiles());
        Text player2 = new Text("2 Score: " + board.getOTiles());
        player1.setStyle("-fx-font: 16 Courier;");
        player2.setStyle("-fx-font: 16 Courier;");
        whosTurn.setStyle("-fx-font: 12 Courier;");
        this.add(whosTurn, 0, 5);
        this.add(player1, 0, 7);
        this.add(player2, 0, 9);
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
