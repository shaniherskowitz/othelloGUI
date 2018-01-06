import java.util.List;

public abstract class Player {
    protected Tile symbol;

    public Player(Tile name) {
        this.symbol = name;
    }


    Tile getSymbol() {
        return symbol;
    }

    char getSymbolMeaning() {
        if (symbol == Tile.O) return 'O';
        else return 'X';
    }

    /**
     * The method gets the player's move from the given optional moves list.
     *
     * @param movesList The optional moves list.
     * @param print     The gameUI.
     * @param board     The game's board.
     * @return The player's chosen move.
     */
    public abstract Move getTurnsMove(List<Move> movesList, GameUI print, Board board);
}
