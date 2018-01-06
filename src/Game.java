
public class Game {
    private Player player1;
    private Player player2;
    private GameUI print;
    private int size;

    public Game(Player one1, Player two1, GameUI print1, int size1) {
        player1 = one1;
        player2 = two1;
        size = size1;
        print = print1;
    }

    public void run() {
        GameLogic logic = new RegularGameLogic();
        Board board = new Board(size);
        GameStatus gameStatus = GameStatus.IN_PROGRESS;
        GameStatus player1TurnStatus = GameStatus.NOT_STARTED, player2TurnStatus = GameStatus.NOT_STARTED;

        while (gameStatus == GameStatus.IN_PROGRESS) {
            player1TurnStatus = logic.turn(player1, board, print);
            if (player1TurnStatus == GameStatus.HAS_NO_MOVES && player2TurnStatus == GameStatus.HAS_NO_MOVES) {
                gameStatus = GameStatus.HAS_NO_MOVES;
                break;
            }
            if (player1TurnStatus == GameStatus.FULL_BOARD) {
                gameStatus = GameStatus.FULL_BOARD;
                break;
            }
            player2TurnStatus = logic.turn(player2, board, print);
            if (player1TurnStatus == GameStatus.HAS_NO_MOVES && player2TurnStatus == GameStatus.HAS_NO_MOVES) {
                gameStatus = GameStatus.HAS_NO_MOVES;
                break;
            }
            if (player2TurnStatus == GameStatus.FULL_BOARD) {
                gameStatus = GameStatus.FULL_BOARD;
                break;
            }
        }
        print.printBoard(board);
        print.declareWinner(board, gameStatus);

    }
}
