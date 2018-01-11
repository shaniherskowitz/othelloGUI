package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import othelloApp.GUI.BoardGUI;
import othelloApp.GUI.GraphicUI;
import othelloApp.GUI.ScoreGUI;
import othelloGame.*;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.exit;


public class OthelloController implements Initializable {
    private BoardGUI board;
    private GraphicUI gui;
    private Color player1Color;
    private Color player2Color;
    private int boardSize;

    @FXML
    private HBox root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.player1Color = Color.rgb(255, 191, 247);
        this.player2Color = Color.rgb(255, 224, 251);
        this.boardSize = 6;

        ScoreGUI scoreGUI = new ScoreGUI(player1Color, player2Color);
        this.board = new BoardGUI(new Board(boardSize), player1Color, player2Color, true, scoreGUI);
        this.gui = new GraphicUI(board);
        board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);

        printFirstMoves();
        board.draw();


        scoreGUI.setPrefWidth(40);
        scoreGUI.setPrefHeight(20);
        root.getChildren().add(1, scoreGUI);
        scoreGUI.draw(board.getBoard(), true);

        updateScreenSize();


    }
    private void updateScreenSize() {

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            board.setPrefWidth(boardNewWidth);

            printFirstMoves();
            //board.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            board.setPrefHeight(newValue.doubleValue());
            printFirstMoves();
            //board.draw();
        });
    }
    private void printFirstMoves() {
        GameLogic gl = new RegularGameLogic();
        List<Move> movesList = gl.getMovesList(Tile.X, board.getBoard());
        gui.printMoves('X',movesList);
    }

    @FXML
    protected void startGame() {
        GameLogic gl = new RegularGameLogic();
        List<Move> movesList = gl.getMovesList(Tile.X, board.getBoard());

        gui.printMoves('X',movesList);
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
