package othelloApp;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import othelloGame.*;

import java.util.List;

public class GraphicUI implements GameUI {
    private GridPane root;
    private BoardGUI board;

    GraphicUI(GridPane root, BoardGUI board) {
        this.root = root;
        this.board = board;
    }

    public void printBoard(Board board2) {
        BoardGUI board1 = new BoardGUI(board2);
        board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.add(board,0 , 0);
        //root.getChildren().add(0, board1);
        board.draw();


    }


    public void userInput() {

    }

    public void repeatUserInput() {

    }


    public void printMoves(char symbol, List<Move> movesList) {
        for (int i = 0; i < movesList.size(); i++) {
            javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(50, 50, Color.rgb(0, 0, 0));
            rect.setX(movesList.get(i).getPoint().getX());
            rect.setY(movesList.get(i).getPoint().getY());
            board.add(rect, movesList.get(i).getPoint().getX(), movesList.get(i).getPoint().getY());
        }
    }


    public void declareWinner(Board board, GameStatus gameStatus) {

    }


    public void movesListIsEmpty() {

    }

    public void played(Point p, char player) {

    }
}
