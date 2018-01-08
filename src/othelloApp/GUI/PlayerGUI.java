package othelloApp.GUI;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import othelloGame.*;

import java.util.List;

public class PlayerGUI {
    private GridPane grid;
    private int cellWidth;
    private int cellHeight;
    private Color color;
    private Tile symbol;
    private BoardGUI board;

    public PlayerGUI(GridPane grid, int cellWidth, int cellHeight, Color color1,Tile symbol, BoardGUI board) {
        this.grid = grid;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.color = color1;
        this.symbol = symbol;
        this.board = board;

    }
    public void draw(int col, int row) {
        Circle circle = new Circle(cellWidth, cellHeight, cellHeight/2 -2);
        circle.setFill(color);
        grid.add(circle, col, row);
        grid.getChildren().remove(circle);
        grid.add(circle, col, row);
    }

    public void Turn() {
        GameLogic gl = new RegularGameLogic();
        List<Move> movesList = gl.getMovesList(symbol, board.getBoard());
        GraphicUI graphicUI = new GraphicUI(board);
        graphicUI.printMoves(symbol.name().charAt(0), movesList);
    }

}
