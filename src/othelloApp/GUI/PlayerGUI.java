package othelloApp.GUI;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerGUI {
    private GridPane grid;
    private int cellWidth;
    private int cellHeight;
    private Color color;

    public PlayerGUI(GridPane grid, int cellWidth, int cellHeight, Color color1) {
        this.grid = grid;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.color = color1;

    }
    public void draw(int col, int row) {
        Circle circle = new Circle(cellWidth, cellHeight, cellHeight/2 -2);
        circle.setFill(color);
        grid.add(circle, col, row);
        grid.getChildren().remove(circle);
        grid.add(circle, col, row);
    }
}
