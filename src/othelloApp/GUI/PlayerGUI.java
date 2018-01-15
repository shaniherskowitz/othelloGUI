package othelloApp.GUI;

import javafx.geometry.HPos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othelloGame.*;
import java.util.List;
import java.util.ListIterator;

/**
 * Defining a PlayerGUI class.
 */
public class PlayerGUI {
    private GridPane grid;
    private Color color;
    private Tile symbol;
    private BoardGUI board;

    /**
     * The PlayerGUI constructor.
     * @param grid The board gam grid.
     * @param color1 The color of the player.
     * @param symbol The symbol of the player.
     * @param board The game board.
     */
    public PlayerGUI(GridPane grid, Color color1, Tile symbol, BoardGUI board) {
        this.grid = grid;
        this.color = color1;
        this.symbol = symbol;
        this.board = board;

    }

    /**
     * The method draws the player on the board.
     * @param col The column index.
     * @param row The row index.
     * @param tileSize The tile size.
     */
    public void draw(int col, int row, int tileSize) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        Circle outerCircle = new Circle(tileSize, tileSize, ((0.8 *tileSize) / 2));
        outerCircle.setFill(Color.rgb(158, 18, 113));
        outerCircle.setEffect(dropShadow);
        Circle circle = new Circle(tileSize, tileSize, (0.7 *tileSize) / 2 );
        circle.setFill(color);
        grid.setHalignment(outerCircle, HPos.CENTER);
        grid.setHalignment(circle, HPos.CENTER);
        grid.add(outerCircle, col, row);
        grid.add(circle, col, row);
    }

    /**
     * The method returns the symbol of the player.
     * @return The player's symbol.
     */
    public Tile getSymbol() {
        return symbol;
    }

    /**
     * The method returns the char of the symbol.
     * @return The char of the symbol.
     */
    public char getChar() {
        if (symbol == Tile.O) return 'O';
        return 'X';
    }

    /**
     * The method returns the player's color.
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * The method returns true if the next player has a move, and else false.
     * @param move The current player's move.
     * @return True if the next player has a move, else false.
     */
    public boolean Turn(Move move) {
        GameLogic gl = new RegularGameLogic();
        List<Move> movesList = gl.getMovesList(symbol, board.getBoard());
        if (move.getPoint().equals(new Point(-5, -5))) return false;
        if (movesList.isEmpty()) return true;
        if (inMoves(move, movesList)) {
            try {
                gl.flipTiles(board.getBoard(), symbol, move.getPoint());
            } catch (Exception e) {}
            return true;
        }
        return false;
    }

    /**
     * The method returns true if the movesList contains the move.
     * @param move The given move.
     * @param movesList The given movesList.
     * @return True if move is in movesList and else false.
     */
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
