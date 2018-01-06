import java.util.ArrayList;
import java.util.List;
public interface GameUI {

    /**
     * The method asks user for input.
     */
    public void userInput();

    /**
     * The method lets the user know there's a problem with input.
     */
    public void problemWithInput();

    /**
     * The method prints the given board.
     * @param board given board.
     */
    public void printBoard(Board board);

    /**
     * The method asks user to enter move again
     */
    public void repeatUserInput();

    /**
     * The method prints the given player moves.
     * @param symbol given players symbol.
     * @param movesList The given moves.
     */
    public void printMoves(char symbol, List<Move> movesList);

    /**
     * The method declares the game's winner.
     * @param board The game's board.
     * @param gameStatus The game status.
     */
    public void declareWinner(Board board, GameStatus gameStatus);

    /**
     * The menu prints the menu.
     */
    public void showMenu();

    /**
     * The method prints the user's choice for menu option.
     * @param choice The user's choice of type of game.
     */
    public void gameStart(int choice);

    /**
     * The method prints if the moves list is empty.
     */
    public void movesListIsEmpty();

    /**
     * The method prints the move the computer chose.
     * @param p The moves point.
     */
    public void computerMove(Point p);
    /**
     * The method prints the move the player chose.
     * @param p point player played
     * @param player that played
     */
    public void played(Point p, char player);

}
