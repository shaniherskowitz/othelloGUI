package othelloApp;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import othelloGame.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameGUI extends GridPane {
    private Game game;
    private BoardGUI board;


    public GameGUI(BoardGUI board) {
        this.board = board;
        this.game = new Game(new HumanPlayer(Tile.X), new HumanPlayer(Tile.O), new GraphicUI(this, board), 8);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void draw() {
        this.getChildren().clear();
        GraphicUI g = new GraphicUI(this, board);
        List<Move> list = new ArrayList<>();
        list.add(new Move(new Point(2,2), 2));
        g.printBoard(new Board(8));
        g.printMoves('X', list);




    }

}
