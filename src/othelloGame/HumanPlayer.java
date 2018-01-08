package othelloGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ListIterator;

import static java.lang.System.exit;


public class HumanPlayer extends Player {
    private static final int NOT_INDEX = -1;

    public HumanPlayer(Tile symbol) {
        super(symbol);
    }

    public Move getTurnsMove(List<Move> movesList, GameUI print, Board board) {
        print.printBoard(board);
        if (movesList.isEmpty()) {
            print.movesListIsEmpty();
            return new Move(new Point(NOT_INDEX, NOT_INDEX));
        }
        print.printMoves(getSymbolMeaning(), movesList);
        Move move = print.getUserInput();
        while (!inMoves(move, movesList)) {
            print.repeatUserInput();
            print.printMoves(getSymbolMeaning(), movesList);
            move = print.getUserInput();
        }
        return move;
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
}
