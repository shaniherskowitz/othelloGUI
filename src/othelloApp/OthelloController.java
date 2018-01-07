package othelloApp;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import othelloApp.GUI.BoardGUI;
import othelloApp.GUI.GameGUI;
import othelloApp.GUI.GraphicUI;
import othelloGame.Board;
import othelloGame.Game;
import othelloGame.HumanPlayer;
import othelloGame.Tile;

import java.net.URL;
import java.util.ResourceBundle;

public class OthelloController implements Initializable {
    @FXML
    private HBox root;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BoardGUI board = new BoardGUI(new Board(8));
        /*GameGUI game = new GameGUI(board);
        game.setPrefWidth(400);
        game.setPrefHeight(400);
        root.getChildren().add(0, game);*/
        board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);
        board.draw();

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            board.setPrefWidth(boardNewWidth);
            board.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            board.setPrefHeight(newValue.doubleValue());
            board.draw();
        });
    }
    @FXML
    protected void startGame() {
        BoardGUI board = new BoardGUI(new Board(8));
        Game game = new Game(new HumanPlayer(Tile.X), new HumanPlayer(Tile.O), new GraphicUI(board), 8);
        game.run();
    }
}
