package othelloApp;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import othelloApp.GUI.BoardGUI;
import othelloApp.GUI.GameGUI;
import othelloApp.GUI.GraphicUI;
import othelloGame.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class OthelloController implements Initializable {
    private BoardGUI board;
    @FXML
    private HBox root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new BoardGUI(new Board(8));
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

        GameLogic logic = new RegularGameLogic();
        Board board1 = new Board(8);
        boolean turn = true;
        GameStatus gameStatus = GameStatus.IN_PROGRESS;
        GameStatus player1TurnStatus = GameStatus.NOT_STARTED, player2TurnStatus = GameStatus.NOT_STARTED;

        /*if(turn) {
            player1TurnStatus = logic.turn(player1, board1, print);
        } else player2TurnStatus = logic.turn(player2, board1, print);*/

        GameLogic gl = new RegularGameLogic();
        List<Move> movesList = gl.getMovesList(Tile.O, board.getBoard());
        GraphicUI graphicUI = new GraphicUI(board);
        graphicUI.printMoves('O', movesList);


    }
    @FXML
    protected void endGame() {
        exit(1);
    }
    @FXML
    protected void settings() {

    }
}
