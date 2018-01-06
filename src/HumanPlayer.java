import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ListIterator;


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
        Move move = getUserInput(print);
        while (!inMoves(move, movesList)) {
            print.repeatUserInput();
            print.printMoves(getSymbolMeaning(), movesList);
            move = getUserInput(print);
        }
        return move;
    }

    public Move getUserInput(GameUI print) {
        print.userInput();
        int i = 0, j = 0;
        String point = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            point = reader.readLine();
            i = Character.getNumericValue(point.charAt(0));
            j = Character.getNumericValue(point.charAt(2));
        } catch (Exception e) {
        }


        //print.problemWithInput(); check for problem


        return new Move(new Point(i - 1, j - 1));
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
