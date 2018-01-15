package othelloApp.GUI;

import othelloGame.*;
import java.util.List;

/**s
 * Defining a GraphicUI class which implements the GameUI interface
 * to control the game graphics.
 */
public class GraphicUI implements GameUI {

    private BoardGUI board;

    /**
     * The GraphicUI constructor.
     * @param board The game board.
     */
    public GraphicUI(BoardGUI board) { this.board = board; }

    public void printBoard(Board board2) {
        board.updateBoard(board2);
        board.draw();
    }

    public void userInput() {}

    public void repeatUserInput() {}

    public void printMoves(char symbol, List<Move> movesList) {
        board.getChildren().clear();
        board.updateBoard(board.getBoard());
        board.draw();
        int height = (int) board.getPrefHeight();
        int width = (int) board.getPrefHeight();
        int cellHeight = height / board.getBoard().getSize();
        int cellWidth = width / board.getBoard().getBoard()[0].length;

        for (Move move : movesList) {
            javafx.scene.shape.Rectangle rect;
            rect = new javafx.scene.shape.Rectangle(cellWidth, cellHeight, board.getCurrentPlayer().getColor());
            rect.setX(move.getPoint().getX());
            rect.setY(move.getPoint().getY());
            board.add(rect, move.getPoint().getY(), move.getPoint().getX());
        }
    }

    public void declareWinner(Board board, GameStatus gameStatus) {}

    public void movesListIsEmpty() {}

    public void played(Point p, char player) {}

    public Move getUserInput() { return board.mousePressEvent(); }
}
