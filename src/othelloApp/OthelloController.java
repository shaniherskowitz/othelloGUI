package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import othelloApp.GUI.BoardGUI;
import othelloApp.GUI.GraphicUI;
import othelloApp.GUI.ScoreGUI;
import othelloGame.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.System.exit;


public class OthelloController implements Initializable {
    private BoardGUI board;
    private GraphicUI gui;

    @FXML
    private HBox root;
    //@FXML

    @FXML
    private Button settingsButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Settings settings = Settings.loadSettings();
        Color player1Color = settings.getColorX();
        Color player2Color = settings.getColorY();
        //this.player1Color = Color.rgb(171, 244, 242);
        //this.player2Color = Color.rgb(252, 149, 118);
        boolean whoStarts = true;
        if (!settings.getFirstPlayer().equals("X")) { whoStarts = false; }

        ScoreGUI scoreGUI = new ScoreGUI(player1Color, player2Color);
        this.board = new BoardGUI(new Board(settings.getSize()), player1Color, player2Color, whoStarts, scoreGUI);
        this.gui = new GraphicUI(board);
        board.setPrefWidth(400);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);
        //Button settingsButton = new Button("Settings");
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
        Settings settings = Settings.loadSettings();
        settings.runSettingScene((Stage) root.getScene().getWindow());
    }
}
