package othelloGame;

import java.util.List;

public interface GameLogic {

    /**
     * The method plays a single player's turn.
     * @param player The player with the turn.
     * @param board The current board.
     * @param print The game gameUI.
     * @return The current game status.
     */
    public GameStatus turn(Player player, Board board, GameUI print);

    /**
     * The method returns a list with the possible moves the player can play in
     * his turn.
     * @param player The players symbol.
     * @param board The game's board.
     * @return The moves list.
     */
    public List<Move> getMovesList(Tile player, Board board);

    /**
     * The method sets the given tile at the given point and flips the tiles
     * on the board according to the game logic.
     * @param board The game's board.
     * @param tile The tile to be set.
     * @param location The player's move.
     */
    public void flipTiles(Board board, Tile tile, Point location) throws Exception;
}
