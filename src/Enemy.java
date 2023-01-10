import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Enemy {
    private int x,y, type, rangeStart, rangeEnd;
    private int xa = 1;

    Toolkit t = Toolkit.getDefaultToolkit();
    private Image redVirus = null;
    private Image blueVirus = null;
    private Image yellowVirus = null;

    //width and height of virus is 120px

    //type is 0-2, 0 is red, 1 is blue, 2 is yellow,length is the range of motion
    public Enemy(int xx, int yy, int ttype, int length) {
        x = xx;
        y = yy-70;
        type = ttype;
        rangeStart = x;
        rangeEnd = x+length;
        try {
            redVirus = t.getImage("src/resources/Viruses/Red_Virus.gif");
            blueVirus = t.getImage("src/resources/Viruses/Blue_Virus.gif");
            yellowVirus = t.getImage("src/resources/Viruses/Yellow_Virus.gif");
        } catch (Error e)
        {
            System.out.println("No Image");
        }
    }

    public void move() {
        if (type == 0) {
            if (x+xa > rangeEnd || x+xa < rangeStart) xa*=-1; 
            x+=xa;
        }
    }
    public void paint(Graphics g) {
        g.drawImage(redVirus, x, y, null);
    }
}
