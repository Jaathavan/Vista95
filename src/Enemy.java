import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.Rectangle;

public class Enemy {
    private int x, y, type, rangeStart, rangeEnd;
    private int xa = 1;

    Toolkit t = Toolkit.getDefaultToolkit();
    private Image redVirus = null;
    private Image blueVirus = null;
    private Image yellowVirus = null;
    private boolean isAlive = true;
    // width and height of virus is 120px

    // type is 0-2, 0 is red, 1 is blue, 2 is yellow, length is the range of motion
    public Enemy(int xx, int yy, int ttype, int length) {
        x = xx;
        y = yy - 70;
        type = ttype;
        rangeStart = x;
        rangeEnd = x + length;
        try {
            // redVirus = t.getImage("src/resources/Viruses/Red_Virus.gif");
            // blueVirus = t.getImage("src/resources/Viruses/Blue_Virus.gif");
            // yellowVirus = t.getImage("src/resources/Viruses/Yellow_Virus.gif");
            redVirus = t.getImage("src/resources/Red_Virus.gif");
            blueVirus = t.getImage("src/resources/Blue_Virus.gif");
            yellowVirus = t.getImage("src/resources/Yellow_Virus.gif");
        } catch (Error e) {
            System.out.println("No Image");
        }
    }

    public int getY() {
        return y;
    }

    public void move() {
        if (isAlive) {
            if (type == 0) {
                if (x + xa > rangeEnd || x + xa < rangeStart)
                    xa *= -1;
                x += xa;
            }
            if (type == 1) {
            }
            if (type == 2) {
            }
        }
    }

    public void die() {
        isAlive = false;
        // change image to death or make it disappear
    }

    public Rectangle getBounds() {
        if (isAlive) {
            return new Rectangle(x, y, 70, 70);
        }
        return new Rectangle(0, 0, 0, 0);
    }

    public void paint(Graphics g) {
        if (isAlive) {
            g.drawImage(redVirus, x, y, null);
        }
        g.drawRect(x, y, 70, 70);
    }
}