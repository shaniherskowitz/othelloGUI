package othelloApp;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import othelloApp.GUI.BoardGUI;
import othelloApp.GUI.GameGUI;
import othelloApp.GUI.GraphicUI;
import othelloGame.*;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.exit;


public class OthelloController implements Initializable {
    private BoardGUI board;
    private GraphicUI gui;
    @FXML
    private Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new BoardGUI(new Board(8));
        this.gui = new GraphicUI(board);
        board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);
        //board.draw();

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            board.setPrefWidth(boardNewWidth);
            //board.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            board.setPrefHeight(newValue.doubleValue());
            //board.draw();
        });
    }

    @FXML
    protected void startGame() {
        Player player1 = new HumanPlayer(Tile.X);
        Player player2 = new HumanPlayer(Tile.O);
        //root = board;
        Game game = new Game(player1, player2, gui, board.getBoard());
        //Game game = Game.gameFromSettings(gui, player1, player2);
       /* try {
            root = new FXMLLoader(getClass().getResource("board.fxml"));
        }*/
        //board.reload();
        game.run();
        /*Stage curr = ((Stage)startScene.getScene().getWindow());
        board.draw();
        curr.show();*/

        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
            loader.setRoot(board);
            //loader.setController(board);
            Parent parent = loader.load();
            board.draw();
            ((Stage)startScene.getScene().getWindow()).setScene(new Scene(parent, 400, 400));
        } catch (IOException eox) {
            eox.printStackTrace();
        }*/
       // game.run();
        /*GameLogic logic = new RegularGameLogic();
        Board board1 = new Board(8);
        boolean turn = true;
        GameStatus gameStatus = GameStatus.IN_PROGRESS;
        GameStatus player1TurnStatus = GameStatus.NOT_STARTED, player2TurnStatus = GameStatus.NOT_STARTED;*/

        /*if(turn) {
            player1TurnStatus = logic.turn(player1, board1, print);
        } else player2TurnStatus = logic.turn(player2, board1, print);*/

        /*GameLogic gl = new RegularGameLogic();
        List<Move> movesList = gl.getMovesList(Tile.O, board.getBoard());
        GraphicUI graphicUI = new GraphicUI(board);
        graphicUI.printMoves('O', movesList);
        graphicUI.getUserInput();*/

    }

    @FXML
    protected void endGame() {
        exit(1);
    }

    @FXML
    protected void settings() {
        Stage stage = new Stage();
        //HBox settingsPage = new HBox();
        try {
            GridPane settingsPage = (GridPane) FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene scene = new Scene(settingsPage, 520, 400);
            //scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            Button selectBoardSize = new Button("Select size:");
            Button chooseStartingPlayer = new Button("select startin player:");
            settingsPage.getChildren().add(selectBoardSize);
            settingsPage.getChildren().add(chooseStartingPlayer);
            stage.setTitle("Settings");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.print("Exception"); }
    }
}
