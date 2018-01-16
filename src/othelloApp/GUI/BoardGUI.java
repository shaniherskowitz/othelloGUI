package othelloApp.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import othelloGame.*;
import othelloGame.Point;
import java.util.List;

/**
 * Defining a BoardGUI class to represent the game board.
 */
public class BoardGUI extends GridPane {

    private Board board;
    private Point move;
    private int tileSize;
    private boolean player1turn;
    private PlayerGUI player1;
    private PlayerGUI player2;
    private ScoreGUI score;

    /**
     * The BoardGUI's constructor.
     * @param board The game's board.
     * @param color1 The first player's color.
     * @param color2 The second player's color.
     * @param whoStarts The boolean value to determine the player who starts the game.
     * @param scoreGUI The score GUI.
     */
    public BoardGUI(Board board, Color color1, Color color2, boolean whoStarts, ScoreGUI scoreGUI) {
        this.board = board;
        this.tileSize = (int) this.getPrefHeight()/ this.board.getSize();
        this.player1turn = whoStarts;
        this.player1 = new PlayerGUI(this, color1, Tile.X, this);
        this.player2 = new PlayerGUI(this, color2, Tile.O, this);
        this.score = scoreGUI;
        load();
        playerClick();
    }

    /**
     * The method defines an event handler for game turns.
     */
    private void playerClick() {
        GameLogic gl = new RegularGameLogic();
        GraphicUI gui = new GraphicUI(this);
        this.setOnMousePressed(event -> {
                PlayerGUI current = getCurrentPlayer();
                move = new Point((int) event.getY() / tileSize, (int) event.getX() / tileSize);
                //System.out.println("mouse click detected! " + move.PointToString());
                boolean check = current.Turn(new Move(move, 0));
                List<Move> movesList1 = gl.getMovesList(getOtherPlayer().getSymbol(), getBoard());
                if (check && !movesList1.isEmpty()) player1turn = !player1turn;
                List<Move> movesList = gl.getMovesList(getCurrentPlayer().getSymbol(), getBoard());
                gui.printMoves(getCurrentPlayer().getChar(), movesList);
                if (board.boardFull() || noMoves()) score.declareWinner(board);
        });
    }

    /**
     * The method returns true if the player has no moves, and else false.
     * @return
     */
    private boolean noMoves() {
        GameLogic gl = new RegularGameLogic();
        List<Move> movesList1 = gl.getMovesList(player1.getSymbol(), getBoard());
        List<Move> movesList2 = gl.getMovesList(player2.getSymbol(), getBoard());
        return (movesList1.isEmpty() && movesList2.isEmpty());
    }

    /**
     * The method reloads itself from FXML as board GUI.
     */
    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../files/board.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.draw();
    }

    /**
     * The method draws the board.
     */
    public void draw() {
        this.getChildren().clear();

        int height = (int) this.getPrefHeight();
        this.tileSize = height / board.getSize();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rect = new Rectangle(tileSize, tileSize, new ImagePattern(
                        new Image("othelloApp/pics/tile4.jpeg")));
                rect.setStroke(Color.WHITE);
                this.add(rect, j, i);
                if (board.getBoard()[i][j] == Tile.X) { player1.draw(j, i, tileSize); }
                else if (board.getBoard()[i][j] == Tile.O) { player2.draw(j, i, tileSize); }
            }
        }
        score.draw(board, player1turn);
    }

    /**
     * The method updates the game board object with a given game board.
     * @param board THe given game board.
     */
    public void updateBoard(Board board) {
        this.board = board;
    }

    /**
     * The method returns the game board.
     * @return The game board.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * The method returns the move given a mouse press event.
     * @return The appropriate move.
     */
    public Move mousePressEvent() {
        if (move == null || move.getX() < 0 || move.getY() < 0) move = new Point(-5, -5);
        return new Move(move);
    }

    /**
     * The method returns the player with the current turn.
     * @return The player with the current turn.
     */
    public PlayerGUI getCurrentPlayer() {
        if (player1turn) return player1;
        return player2;
    }

    /**
     * The method returns the player which isn't his turn.
     * @return The player which isn't his turn.
     */
    public PlayerGUI getOtherPlayer() {
        if (!player1turn) return player1;
        return player2;
    }

}
