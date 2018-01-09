package othelloApp.GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import othelloGame.Board;
import othelloGame.Move;
import othelloGame.Point;
import othelloGame.Tile;


import java.io.IOException;


public class BoardGUI extends GridPane {
    private Board board;
   // private Parent scene;

    public BoardGUI(Board board) {
        this.board = board;
        reload();
       // this.scene = null;
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../board.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }*/
    }

    public void reload() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../board.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            Parent scene = fxmlLoader.load();
            //scene.getScene().getWindow();
           // ((Stage)this.getScene().getWindow()).setScene(new Scene(scene, 400, 400));
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

    public class MouseHandler implements EventHandler<MouseEvent> {
        private double x, y;
        @Override
        public void handle(MouseEvent event) {
            x = event.getX();
            y = event.getY();
            //System.out.println("mouse click detected!");
            event.consume();
        }
        public Move returnMove() {
            return processMousePressEvent(x, y);
        }
    }

    public Move mousePressEvent() {
        MouseHandler mouseHandler = new MouseHandler();
        //mouseHandler.handle();
        setOnMousePressed(mouseHandler);
        return mouseHandler.returnMove();
    }


    public Move processMousePressEvent(double x, double y) {
        int boardSize = (int) this.getPrefHeight();
        int cellSize  = boardSize / board.getSize();
        if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) return new Move(new Point());
        return new Move(new Point((int)x/cellSize, (int)y/cellSize));
    }

}
