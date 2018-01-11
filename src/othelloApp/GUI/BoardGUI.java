package othelloApp.GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import othelloGame.*;
import othelloGame.Point;

import java.awt.*;
import java.util.List;


public class BoardGUI extends GridPane {
    private Board board;
    private Point move;
    private int tileSize;
    private boolean player1turn;
    private PlayerGUI player1;
    private PlayerGUI player2;
    private ScoreGUI score;


    public BoardGUI(Board board, Color color1, Color color2, boolean whoStarts, ScoreGUI scoreGUI) {
        this.board = board;
        this.tileSize = 50;
        this.player1turn = whoStarts;
        this.player1 = new PlayerGUI(this, color1, Tile.X, this);
        this.player2 = new PlayerGUI(this, color2, Tile.O, this);
        this.score = scoreGUI;


        load();
        playerClick();


    }

    void playerClick() {
        GameLogic gl = new RegularGameLogic();
        GraphicUI gui = new GraphicUI(this);
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PlayerGUI current = getCurrentPlayer();
                move = new Point((int) event.getY() / tileSize, (int) event.getX() / tileSize);
                //System.out.println("mouse click detected! " + move.PointToString());
                boolean check = current.Turn(new Move(move, 0));

                List<Move> movesList1 = gl.getMovesList(getOtherPlayer().getSymbol(), getBoard());

                if (check && !movesList1.isEmpty()) player1turn = !player1turn;


                List<Move> movesList = gl.getMovesList(getCurrentPlayer().getSymbol(), getBoard());
                gui.printMoves(getCurrentPlayer().getChar(), movesList);
                if (board.boardFull() || noMoves()) score.declareWinner(board);

            }
        });
    }

    private boolean noMoves() {
        GameLogic gl = new RegularGameLogic();
        List<Move> movesList1 = gl.getMovesList(player1.getSymbol(), getBoard());
        List<Move> movesList2 = gl.getMovesList(player2.getSymbol(), getBoard());
        return (movesList1.isEmpty() && movesList2.isEmpty());
    }

    public void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../board.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.draw();
    }

    public void draw() {
        this.getChildren().clear();

        int height = (int) this.getPrefHeight();
        this.tileSize = height / board.getSize();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rect = new Rectangle(tileSize, tileSize, new ImagePattern(new Image("othelloApp/GUI/download (1).jpeg")));
                rect.setStroke(Color.WHITE);
                this.add(rect, j, i);
                if (board.getBoard()[i][j] == Tile.X) {
                    player1.draw(j, i, tileSize);

                } else if (board.getBoard()[i][j] == Tile.O) {
                    player2.draw(j, i, tileSize);
                }

            }
        }
        score.draw(board, player1turn);
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }


    public Move mousePressEvent() {
        if (move == null || move.getX() < 0 || move.getY() < 0) move = new Point(-5, -5);
        return new Move(move);
    }

    public PlayerGUI getCurrentPlayer() {
        if (player1turn) return player1;
        return player2;
    }

    public PlayerGUI getOtherPlayer() {
        if (!player1turn) return player1;
        return player2;
    }

}
