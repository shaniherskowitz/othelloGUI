package othelloGame;

public class ConsoleGame {
    public static void main(String[] args) {
        Player p1 = new HumanPlayer(Tile.X);
        Player p2 = new HumanPlayer(Tile.O);
        GameUI print = new ConsoleUI();


        Game game = new Game(p1, p2, print, 3);
        game.run();
    }

}
