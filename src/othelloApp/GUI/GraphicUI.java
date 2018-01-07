package othelloApp.GUI;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import othelloApp.GUI.BoardGUI;
import othelloGame.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class GraphicUI implements GameUI {

    private BoardGUI board;

    public GraphicUI(BoardGUI board) {
        this.board = board;

    }

    public void printBoard(Board board2) {
        board.updateBoard(board2);
        board.setPrefWidth(400);
        board.setPrefHeight(400);
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

    public  Move getUserInput() {
        userInput();
        int i = 0, j = 0;
        String point = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            point = reader.readLine();
            i = Character.getNumericValue(point.charAt(0));
            j = Character.getNumericValue(point.charAt(2));
        } catch (Exception e) {
        }
        //print.problemWithInput(); check for problem


        return new Move(new Point(i - 1, j - 1));

    }
}
