package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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

/**
 * Defining the OthelloController class to control the game GUI's.
 */
public class OthelloController implements Initializable {

    private BoardGUI board;
    private GraphicUI gui;
    private ScoreGUI scoreGUI;

    @FXML
    private AnchorPane root;
    @FXML
    private Button quitButton;
    @FXML
    private Button mainMenuButton;

    /**
     * The method initializes the FXML the OthelloController controls.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Settings settings = Settings.loadSettings();
        Color player1Color = settings.getColorX();
        Color player2Color = settings.getColorY();
        boolean whoStarts = true;
        if (!settings.getFirstPlayer().equals("X")) { whoStarts = false; }
        scoreGUI = new ScoreGUI(player1Color, player2Color);
        this.board = new BoardGUI(new Board(settings.getSize()), player1Color, player2Color, whoStarts, scoreGUI);
        final int boardSize = 400;
        this.gui = new GraphicUI(board);
        board.setPrefWidth(boardSize);
        board.setPrefHeight(boardSize);
        root.getChildren().add(board);
        printFirstMoves(whoStarts);
        board.draw();

        scoreGUI.setTranslateX(20);
        mainMenuButton.setTranslateX(20);
        quitButton.setLayoutX(20);
        root.getChildren().add(scoreGUI);
        scoreGUI.draw(board.getBoard(), true);
        updateScreenSize(whoStarts);
    }

    /**
     * The method adds listeners to the screen size.
     * @param whoStarts The boolean value which determines which player starts.
     */
    private void updateScreenSize(boolean whoStarts) {

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            board.setPrefWidth(boardNewWidth);
            scoreGUI.setLayoutX(boardNewWidth);
            mainMenuButton.setLayoutX(boardNewWidth);
            quitButton.setLayoutX(boardNewWidth);
            printFirstMoves(whoStarts);
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            board.setPrefHeight(newValue.doubleValue());
            printFirstMoves(whoStarts);
        });
    }

    /**
     * The method prints the starting player's move.
     * @param whoStarts The starting player boolean value.
     */
    private void printFirstMoves(boolean whoStarts) {
        GameLogic gl = new RegularGameLogic();
        Tile tile = Tile.X;
        char symbol = 'X';
        if (!whoStarts) { tile = Tile.O; symbol = 'O'; }
        List<Move> movesList = gl.getMovesList(tile, board.getBoard());
        gui.printMoves(symbol, movesList);
    }

    /**
     * The method quits the game.
     */
    @FXML
    protected void quit() { exit(1); }

    /**
     * The method loads the main menu and displays it.
     */
    @FXML
    protected void loadMainMenu() {
        try {
            Stage stage = (Stage) mainMenuButton.getScene().getWindow();
            AnchorPane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Othello");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
