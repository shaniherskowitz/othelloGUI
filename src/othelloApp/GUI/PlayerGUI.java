package othelloApp.GUI;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othelloGame.*;

import java.util.List;
import java.util.ListIterator;

public class PlayerGUI {
    private GridPane grid;
    ;
    private Color color;
    private Tile symbol;
    private BoardGUI board;

    public PlayerGUI(GridPane grid, Color color1, Tile symbol, BoardGUI board) {
        this.grid = grid;
        this.color = color1;
        this.symbol = symbol;
        this.board = board;

    }

    public void draw(int col, int row, int tileSize) {
        Circle circle = new Circle(tileSize, tileSize, tileSize / 2 - 2);
        circle.setFill(color);
        grid.add(circle, col, row);
        grid.getChildren().remove(circle);
        grid.add(circle, col, row);
    }

    public Tile getSymbol() {
        return symbol;
    }

    public char getChar() {
        if (symbol == Tile.O) return 'O';
        return 'X';
    }

    public Color getColor() {
        return color;
    }

    public boolean Turn(Move move) {

        GameLogic gl = new RegularGameLogic();

        List<Move> movesList = gl.getMovesList(symbol, board.getBoard());

        if (move.getPoint().equals(new Point(-5, -5))) return false;
        if (movesList.isEmpty()) return true;
        if (inMoves(move, movesList)) {
            try {
                gl.flipTiles(board.getBoard(), symbol, move.getPoint());
            } catch (Exception e) {
            }
            return true;
        }
        return false;

    }

    public boolean inMoves(Move move, List<Move> movesList) {
        ListIterator<Move> it = movesList.listIterator();
        while (it.hasNext()) {
            Point next = it.next().getPoint();
            if (next.equals(move.getPoint())) {
                return true;
            }
        }
        return false;
    }

}
