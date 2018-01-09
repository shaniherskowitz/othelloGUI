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
import javafx.stage.Stage;
import othelloApp.GUI.BoardGUI;
import othelloApp.GUI.GameGUI;
import othelloApp.GUI.GraphicUI;
import othelloGame.*;


import javax.swing.*;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import static java.lang.System.exit;


public class OthelloController implements Initializable {
    private BoardGUI board;
    private GraphicUI gui;
    private boolean turn1;
    @FXML
    private HBox root;

    @FXML
    private Button startScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new BoardGUI(new Board(8));
        this.gui = new GraphicUI(board);
        this.turn1 = true;
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
        Player player1 = new HumanPlayer(Tile.X);
        Player player2 = new HumanPlayer(Tile.O);
        Game game = new Game(player1, player2, gui, 8);
        //board.reload();
        //game.run();
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

        GameLogic gl = new RegularGameLogic();
        GraphicUI graphicUI = new GraphicUI(board);
        if(turn1) {
            List<Move> movesList = gl.getMovesList(Tile.O, board.getBoard());
            if (movesList.isEmpty()) {
                gui.movesListIsEmpty();
            }
            gui.printMoves('O', movesList);
            Move move = gui.getUserInput();
            if (move.getPoint().equals(new Point(-5, -5))) return;
            if (inMoves(move, movesList)) {
                gui.setTurn(false);
                turn1 = false;
                try {
                    gl.flipTiles(board.getBoard(), Tile.O, move.getPoint());
                } catch (Exception e) {
                }
            }
        } else {
            List<Move> movesList = gl.getMovesList(Tile.X, board.getBoard());
            if (movesList.isEmpty()) {
                gui.movesListIsEmpty();
            }
            gui.printMoves('X', movesList);
            Move move = gui.getUserInput();
            if (move.getPoint().equals(new Point(-5, -5))) return;
            if (inMoves(move, movesList)) {
                gui.setTurn(true);
                turn1 = true;
                try {
                    gl.flipTiles(board.getBoard(), Tile.X, move.getPoint());
                } catch (Exception e) {
                }
            }
        }

        //System.out.println(player1.getTurnsMove(movesList, gui, board.getBoard()).getPoint().PointToString());

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

    @FXML
    protected void endGame() {
        exit(1);
    }

    @FXML
    protected void settings() {
        JFrame frame = new JFrame("Settings");
        frame.setSize(250, 250);
        JLabel label = new JLabel("Board Size");
        JTextArea textArea = new JTextArea();
        JButton button = new JButton("Add tab to another frame.");
        frame.add(label);
        frame.add(textArea);
        frame.setVisible(true);
        System.out.println(textArea.getText());


    }
}
