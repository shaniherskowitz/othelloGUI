package othelloGame;

public class Point {

    private int x;
    private int y;

    public Point(int xVal, int yVal) {
        x = xVal;
        y = yVal;
    }

    public Point() {
        x = -1;
        y = -1;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    String PointToString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Point p) {
        return (x == p.getX() && y == p.getY());
    }
}