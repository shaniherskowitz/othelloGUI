package othelloApp.GUI;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
        board.reload();
        //board.setPrefWidth(400);
        //board.setPrefHeight(400);
        board.draw();
        //root.getChildren().add(0, board1);



    }


    public void userInput() {

    }

    public void repeatUserInput() {

    }


    public void printMoves(char symbol, List<Move> movesList) {
        for (int i = 0; i < movesList.size(); i++) {
            javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(50, 50, Color.rgb(160, 40, 79));
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

    public Move getUserInput() {
        Move move =  board.mousePressEvent();
        return move;
    }


}
