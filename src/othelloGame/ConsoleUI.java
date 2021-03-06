package othelloGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ListIterator;

public class ConsoleUI implements GameUI {

    public ConsoleUI() {
    }

    public void printMoves(char symbol, List<Move> movesList) {
        System.out.println(symbol + ": It's your move");
        System.out.println("Your possible moves: ");
        if (!movesList.isEmpty()) {
            ListIterator<Move> it = movesList.listIterator();
            while (it.hasNext()) {
                Move next = it.next();
                Point p = new Point(next.getPoint().getX() + 1, next.getPoint().getY() + 1);
                System.out.print(p.PointToString() + " ");
            }
            System.out.println();
        }
    }

    public void movesListIsEmpty() {
        System.out.println("No possible moves. Play passes back to the other player.");
    }

    public void printBoard(Board board) {
        int size = board.getSize();
        System.out.println();
        System.out.print(" | ");
        int i;
        for (i = 1; i <= size; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        for (i = 0; i < size; i++) {
            int j;
            for (j = 0; j < 4 * (size + 1); j++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.print(i + 1 + "| ");
            for (j = 0; j < size; j++) {
                System.out.print(board.getTile(i, j) + " | ");
            }
            System.out.println();

        }

    }

    public void declareWinner(Board board, GameStatus gameStatus) {
        if (gameStatus == GameStatus.HAS_NO_MOVES) {
            System.out.println("GAME OVER");
            System.out.println("NO MOVES LEFT FOR BOTH PLAYERS...");
            return;
        }
        if (board.isTie()) {
            System.out.println("It's a tie!!!");
            return;
        }
        System.out.println("Player " + board.getWinnerSymbol() + " is the winner!!!");
        //cout << "Your score is: " << board.getXTiles();// not sure is needed bc score will be only full board.

    }

    public void problemWithInput() {
        System.out.println("Value entered not compatible! Please enter again:");
    }

    public void repeatUserInput() {
        System.out.println("Cant make move! Please enter one of your possible moves - row col");
    }

    public void userInput() {
        System.out.println("Please enter your move - row col:");
    }

    public void showMenu() {
        System.out.println("Please select opponent:");
        System.out.println("Option 1 - Human othelloGame.Player");
        System.out.println("Option 2 - Computer othelloGame.Player");
        System.out.println("Option 3 - Remote othelloGame.Player");

    }

    public void gameStart(int choice) {
        System.out.println();
        System.out.println("You have selected to play vs ");
        if (choice == 1) System.out.println("Human othelloGame.Player!");
        else if (choice == 2) System.out.println("Computer othelloGame.Player!");
        else if (choice == 3) System.out.println("Remote othelloGame.Player!");

        System.out.println("Your game will begin now!");
        System.out.println();
    }

    public void computerMove(Point p) {
        System.out.println("Computer has chosen move: " + p.getX() + 1 + "," + p.getY() + 1);
    }

    public void played(Point p, char player) {
        System.out.println(player + " played: (" + (p.getX() + 1) + "," + (p.getY() + 1) + ")");
    }

    public Move getUserInput() {
        userInput();
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

}
