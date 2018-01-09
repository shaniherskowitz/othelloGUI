package othelloApp.GUI;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
    private boolean turn;

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public GraphicUI(BoardGUI board) {
        this.board = board;
        this.turn = true;

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
        board.getChildren().clear();
        board.updateBoard(board.getBoard());
        board.draw();
        int height = (int) board.getPrefHeight();
        int width = (int) board.getPrefHeight();
        int cellHeight = height / board.getBoard().getSize();
        int cellWidth = width / board.getBoard().getBoard()[0].length;

        for (int i = 0; i < movesList.size(); i++) {
            javafx.scene.shape.Rectangle rect;
            if (!turn) rect = new javafx.scene.shape.Rectangle(cellWidth, cellHeight,
                    Color.rgb(255, 163, 224));
            else rect = new javafx.scene.shape.Rectangle(cellWidth, cellHeight,
                    Color.rgb(206, 70, 160));
            rect.setX(movesList.get(i).getPoint().getX());
            rect.setY(movesList.get(i).getPoint().getY());
            board.add(rect, movesList.get(i).getPoint().getY(), movesList.get(i).getPoint().getX());
        }
        board.reload();

    }


    public void declareWinner(Board board, GameStatus gameStatus) {

    }


    public void movesListIsEmpty() {

    }

    public void played(Point p, char player) {

    }

    public Move getUserInput() {

        return board.mousePressEvent();

    }


}
