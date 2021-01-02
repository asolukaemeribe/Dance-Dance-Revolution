import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RightArrow extends GameObj {
    public static final String RIGHT_ARROW = "files/movingRight.png";

    public static final int SIZE = 150;

    private static BufferedImage img;

    public RightArrow() {
        super(SIZE, SIZE, ArrowDirection.RIGHTARROW);

        try {
            img = ImageIO.read(new File(RIGHT_ARROW));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public String getString() {
        return "RIGHT";
    }
}