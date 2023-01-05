import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Player {
    private int x,y;
    private int xa = 0;
    private int ya = 0;

    private boolean left = false, right = false, space = false;

    private BufferedImage player = null;

    public Player() {
        this.x = 200;
        this.y = 200;
        try {
            player = ImageIO.read(new File("src/resources/Balmer/ideal_1.png"));
        } catch (IOException e)
        {
            System.out.println("No Image");
        }
    }
    
    public BufferedImage getPlayer() {
        return player;
    }

    public void setPlayer(String src) {
        try {
            player = ImageIO.read(new File("src/resources/Balmer/" + src));
        } catch (IOException e)
        {
            System.out.println("No Image");
        }
    }

    public void keyPressed(KeyEvent e) {
		// This checks to see which key was pressed, and then sets the appropriate
		// Boolean to true
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			space = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		// When the key is released, set the Boolean to false, and change
		// acceleration to 0
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
			xa = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
			xa = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_W) {
			space = false;
		}
	}

    public void move() {
		// Base your acceleration on the Booleans set by your keyPressed method
		if (right) {
			xa = 2;
		}
		if (left) {
			xa = -2;
		}

		x += xa;
		y += ya;
	}

    public void paint(Graphics g) {
        if (right) {
            setPlayer("walking_1.png");
            g.drawImage(player, x, y, null);
        }
        else if (left) {
            setPlayer("walking_1.png");
            g.drawImage(player, x + player.getWidth(), y, -player.getWidth(), player.getHeight(), null);
        } else {
            setPlayer("ideal_1.png");
            g.drawImage(player, x, y, null);
        }       
    }
}