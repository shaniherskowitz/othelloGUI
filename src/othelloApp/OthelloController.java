package othelloApp;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
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
        GameGUI game = new GameGUI(board);
        game.setPrefWidth(400);
        game.setPrefHeight(400);
        root.getChildren().add(0, game);
        /*board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);*/
        game.draw();

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            game.setPrefWidth(boardNewWidth);
            game.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            game.setPrefHeight(newValue.doubleValue());
            game.draw();
        });
    }
}
