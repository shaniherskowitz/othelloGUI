package othelloGame;

import java.util.List;
public interface GameUI {

    /**
     * The method asks user for input.
     */
    void userInput();

    /**
     * The method prints the given board.
     * @param board given board.
     */
    void printBoard(Board board);

    /**
     * The method asks user to enter move again
     */
    void repeatUserInput();

    /**
     * The method prints the given player moves.
     * @param symbol given players symbol.
     * @param movesList The given moves.
     */
    void printMoves(char symbol, List<Move> movesList);

    /**
     * The method declares the game's winner.
     * @param board The game's board.
     * @param gameStatus The game status.
     */
    void declareWinner(Board board, GameStatus gameStatus);


    /**
     * The method prints if the moves list is empty.
     */
    void movesListIsEmpty();

    /**
     * The method prints the move the player chose.
     * @param p point player played
     * @param player that played
     */
    void played(Point p, char player);


    Move getUserInput();

}
