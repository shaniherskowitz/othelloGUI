package othelloGame;


public class Board {
    private Tile board[][];
    private int size;
    private int xTiles;
    private int oTiles;
    public Board(int size1) {

        this.size = size1;
        this.board = new Tile[size][];

        int i, j;
        for (i = 0; i < size; i++) {
            board[i] = new Tile[size];
            for (j = 0; j < size; j++) {
                board[i][j] = Tile.EMPTY;
            }
        }
        i = size / 2 - 1;
        j = size / 2;
        board[i][i] = Tile.O;
        board[i][j] = Tile.X;
        board[j][i] = Tile.X;
        board[j][j] = Tile.O;
        xTiles = 2;
        oTiles = 2;

    }


    public int getSize() {
        return size;
    }

    public Tile[][] getBoard() {
        return board;
    }

    void setTile(Point point, Tile symbol) {

        board[point.getX()][point.getY()] = symbol;
        if (symbol == Tile.O) oTiles++;
        if (symbol == Tile.X) xTiles++;

    }

    public int getXTiles() {
        return xTiles;
    }

    public int getOTiles() {
        return oTiles;
    }

    public boolean boardFull() {
        return (xTiles + oTiles == size * size);
    }

    boolean isOppositeSymbol(Tile symbol, int row, int col) {
        return board[row][col] != symbol && board[row][col] != Tile.EMPTY;
    }

    boolean isSameSymbol(Tile symbol, int row, int col) {
        return board[row][col] == symbol;
    }

    void flipXTiles() {
        xTiles++;
        oTiles--;
    }

    void flipOTiles() {
        oTiles++;
        xTiles--;
    }

    void flipTile(int row, int col) {
        if (board[row][col] == Tile.O) board[row][col] = Tile.X;
        else if (board[row][col] == Tile.X) board[row][col] = Tile.O;

    }

    char getTile(int i, int j) {
        if (board[i][j] == Tile.X) return 'X';
        if (board[i][j] == Tile.O) return 'O';
        return ' ';
    }

    char getWinnerSymbol() {
        if (xTiles > oTiles) return 'X';
        if (xTiles < oTiles) return 'O';
        return ' ';
    }

    boolean isTie() {
        return xTiles == oTiles;
    }
}