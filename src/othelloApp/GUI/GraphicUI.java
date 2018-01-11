package othelloApp.GUI;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import othelloGame.*;

import java.util.List;

public class GraphicUI implements GameUI {

    private BoardGUI board;


    public GraphicUI(BoardGUI board) {
        this.board = board;


    }

    public void printBoard(Board board2) {
        board.updateBoard(board2);
        board.draw();

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
            rect = new javafx.scene.shape.Rectangle(cellWidth, cellHeight, board.getCurrentPlayer().getColor());
            rect.setX(movesList.get(i).getPoint().getX());
            rect.setY(movesList.get(i).getPoint().getY());
            board.add(rect, movesList.get(i).getPoint().getY(), movesList.get(i).getPoint().getX());
        }

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
