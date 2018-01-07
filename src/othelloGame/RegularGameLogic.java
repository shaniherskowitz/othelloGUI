package othelloGame;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RegularGameLogic implements GameLogic {

    public RegularGameLogic() {
    }


    public GameStatus turn(Player player, Board board, GameUI gameUI) {
        List<Move> movesList = getMovesList(player.getSymbol(), board);
        Move move = player.getTurnsMove(movesList, gameUI, board);
        if (move.getPoint().equals(new Point(-1, -1))) return GameStatus.HAS_NO_MOVES;
        try {
            flipTiles(board, player.getSymbol(), move.getPoint());
        } catch (Exception e) {
        }
        gameUI.played(move.getPoint(), player.getSymbolMeaning());
        if (board.boardFull()) return GameStatus.FULL_BOARD;
        return GameStatus.IN_PROGRESS;
    }

    public List<Move> getMovesList(Tile playerSymbol, Board board) {
        List<Move> movesList = new ArrayList<>();
        Tile gameBoard[][] = board.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (gameBoard[i][j] == playerSymbol) {
                    List<Move> currentMovesList = findMoves(i, j, playerSymbol, board);
                    movesList = mergeMovesList(movesList, currentMovesList);
                }
            }
        }
        return movesList;
    }

    private List<Move> findMoves(int row, int col, Tile symbol, Board board) {
        Point point = new Point(row, col);
        List<Move> movesList = new ArrayList<>();
        for (int dir1 = 1; dir1 >= -1; dir1--) {
            for (int dir2 = 1; dir2 >= -1; dir2--) {
                if (dir1 == 0 && dir2 == 0) continue;
                int counter = getOptionalMove(board, point, symbol, dir1, dir2, 0);
                if (counter > 0) {
                    movesList.add(new Move(new Point(row + (counter + 1) * dir1, col + (counter + 1) * dir2), counter));
                }
            }
        }
        return movesList;
    }

    private List<Move> mergeMovesList(List<Move> movesList, List<Move> currentMovesList) {
        ListIterator<Move> it = currentMovesList.listIterator();
        boolean contains = false;
        while (it.hasNext()) {
            Move current = it.next();
            ListIterator<Move> it1 = movesList.listIterator();
            while (it1.hasNext()) {
                Move next = it1.next();
                if (current.getPoint().equals(next.getPoint())) {
                    next.mergeMove(current);
                    contains = true;
                    break;
                }
            }
            if (!contains) movesList.add(current);

        }
        return movesList;
    }

    private boolean inBound(int boardSize, int row, int col) {
        return (row >= 0 && row < boardSize && col >= 0 && col < boardSize);
    }

    private int getOptionalMove(Board board, Point point, Tile symbol, int dir1, int dir2, int sum) {
        int size = board.getSize();
        int row = point.getX();
        int col = point.getY();
        Tile gameBoard[][] = board.getBoard();

        if (!inBound(size, row, col) || !inBound(size, row + dir1, col + dir2)) return 0;
        if (board.isOppositeSymbol(symbol, row + dir1, col + dir2)) {
            if (!inBound(size, row + 2 * dir1, col + 2 * dir2)) return 0;
            if (board.isSameSymbol(symbol, row + 2 * dir1, col + 2 * dir2)) return 0;
            if (gameBoard[row + 2 * dir1][col + 2 * dir2] == Tile.EMPTY) return ++sum;
            return getOptionalMove(board, new Point(row + dir1, col + dir2), symbol, dir1, dir2, ++sum);
        }
        return 0;
    }

    private void traverseBoard(Board gameBoard, Point point, int dir1, int dir2, Tile tile) {
        Tile board[][] = gameBoard.getBoard();
        int row = point.getX();
        int col = point.getY();
        int size = gameBoard.getSize();

        if (!inBound(size, row, col) || !inBound(size, row + dir1, col + dir2)) return;
        if (board[row + dir1][col + dir2] == Tile.EMPTY) return;
        if (gameBoard.isSameSymbol(tile, row + dir1, col + dir2)) return;
        if (!inBound(size, row + 2 * dir1, col + 2 * dir2)) return;
        if (gameBoard.isOppositeSymbol(tile, row + dir1, col + dir2) &&
                board[row + 2 * dir1][col + 2 * dir2] == Tile.EMPTY)
            return;
        if (gameBoard.isOppositeSymbol(tile, row + dir1, col + dir2) &&
                gameBoard.isOppositeSymbol(tile, row + 2 * dir1, col + 2 * dir2)) {
            traverseBoard(gameBoard, new Point(row + dir1, col + dir2), dir1, dir2, tile);
        }
        if (gameBoard.isOppositeSymbol(tile, row + dir1, col + dir2) &&
                gameBoard.isSameSymbol(tile, row + 2 * dir1, col + 2 * dir2)) {
            gameBoard.flipTile(row + dir1, col + dir2);
            updateBoardCounters(gameBoard, tile);
        }
    }

    public void flipTiles(Board board, Tile tile, Point location) throws Exception {
        if (location == new Point(-1, -1)) throw new Exception("tile location not in range gameLogic/flipTiles");
        board.setTile(location, tile);
        int i, j;
        for (i = 1; i >= -1; i--) {
            for (j = 1; j >= -1; j--) {
                if (i == 0 && j == 0) continue;
                traverseBoard(board, new Point(location.getX(), location.getY()), i, j, tile);
            }
        }
    }

    private void updateBoardCounters(Board board, Tile tile) {
        if (tile == Tile.X) board.flipXTiles();
        if (tile == Tile.O) board.flipOTiles();
    }
}
