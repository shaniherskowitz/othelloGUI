
public class Move {
    private Point move;
    private int scoreCounter;

    Move(Point move) {
        this.move = move;
        this.scoreCounter = 0;
    }

    Move() {
        this.move = new Point();
        this.scoreCounter = 0;
    }

    Move(Point move, int counter) {
        this.move = new Point(move.getX(), move.getY());
        this.scoreCounter = counter;
    }

    void updateCounter(int counter) {
        this.scoreCounter += counter;
    }

    int getScoreCounter() {
        return scoreCounter;
    }

    Point getPoint() {
        return move;
    }

    void mergeMove(Move other) {
        this.scoreCounter += other.scoreCounter;
    }

    boolean equals(Move move) {
        return (this.move == move.move && this.scoreCounter == move.scoreCounter);
    }

    boolean smaller(Move move) {
        if (this.move.getX() == move.move.getX()) {
            return (this.move.getY() < move.move.getY());
        }
        return (this.move.getX() < move.move.getX());
    }
}
