package othelloApp;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import othelloGame.Board;

import java.net.URL;
import java.util.ResourceBundle;

public class OthelloController implements Initializable {
    @FXML
    private HBox root;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BoardGUI board = new BoardGUI(new Board(8));
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
}
