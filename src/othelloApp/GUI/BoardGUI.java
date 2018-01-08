package othelloApp.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import othelloGame.Board;
import othelloGame.Tile;


import java.io.IOException;


public class BoardGUI extends GridPane {
    private Board board;

    public BoardGUI(Board board) {
        this.board = board;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../board.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void draw() {
        this.getChildren().clear();

        int height = (int) this.getPrefHeight();
        //int width = (int) this.getPrefWidth();
        int width = (int) this.getPrefHeight();

        int cellHeight = height / board.getSize();
        int cellWidth = width / board.getBoard()[0].length;

        PlayerGUI player1 = new PlayerGUI(this, cellWidth, cellHeight,
                Color.rgb(255, 163, 224), Tile.X, this);
        PlayerGUI player2 = new PlayerGUI(this, cellWidth, cellHeight,
                Color.rgb(206, 70, 160), Tile.O, this);

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Rectangle rect = new Rectangle(cellWidth, cellHeight, Color.rgb(122, 0, 69));
                rect.setStroke(Color.rgb(252, 239, 255));
                this.add(rect, j, i);
                if (board.getBoard()[i][j] == Tile.X) {
                    player1.draw(j, i);

                }
                else if (board.getBoard()[i][j] == Tile.O) {
                    player2.draw(j, i);
                }

            }
        }

    }
    public void updateBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {return this.board;}

}
