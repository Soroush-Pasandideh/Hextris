import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
    double side;
    int x, y;

    private Hexagon(double x, double y, double side) {
        super(x + side / 2, y + side * (Math.sqrt(3) / 2),
                x + side, y,
                x + side / 2, y - side * (Math.sqrt(3) / 2),
                x - side / 2, y - side * (Math.sqrt(3) / 2),
                x - side, y,
                x - side / 2, y + side * (Math.sqrt(3) / 2)
        );

        this.side = 45 / Math.sqrt(3);
        setStroke(Color.WHITE);
        setFill(Color.DARKCYAN);
    }

    public Hexagon(int x, int y, double side) {
        this(x * (2 * side) - ((x - 1) * (side / 2)),
                y * Hexagon.getHeight(side) + (x % 2 == 0 ? Hexagon.getHeight(side) / 2 : 0), side);
        this.x = x;
        this.y = y;
    }

    public double getHeight() {
        return 2 * side * Math.sqrt(3) / 2;
    }

    public static double getHeight(double side) {
        return 2 * side * Math.sqrt(3) / 2;
    }

    public double getSide() {
        return side;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
