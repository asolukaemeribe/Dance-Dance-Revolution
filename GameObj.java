import java.awt.Graphics;

public abstract class GameObj {

    private int height;
    private int width;

    private ArrowDirection arrowType;

    public GameObj(int height, int width, ArrowDirection arrowType) {
        this.height = height;
        this.width = width;
        this.arrowType = arrowType;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public ArrowDirection getArrowDirection() {
        return arrowType;
    }

    public abstract void draw(Graphics g, int x, int y);

    public abstract String getString();
}