import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {

    private BufferedImage img = null;
    int x = 0;
    private int y = 0;
    int width = 5100;
    int height = 640;

    public Level() {
		 try
		    {
		      img = ImageIO.read(new File("src/resources/Levels/level_1.png"));
		    } catch (IOException e)
		    {
		      System.out.println("No Image");
		    }
	}

    // public void move(int px, int xa) {
    //     // if (px > 510 && px < 4590) {
    //     //     x -= xa;
    //     // }
    // }

    public void paint(Graphics2D g) {
        g.drawImage(img, x, y, width, height, null);
    }
}