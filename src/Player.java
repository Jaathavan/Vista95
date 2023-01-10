import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Player extends Canvas {
    private int x,y;
    private int xa = 0;
    private int ya = 0;

    private boolean left = false, right = false, up = false;
    Toolkit t = Toolkit.getDefaultToolkit();
    private Image player = null;

    public Player() {
        this.x = 200;
        this.y = 200;
        try {
            player = t.getImage("src/resources/Balmer/ideal.gif");
        } catch (Error e)
        {
            System.out.println("No Image");
        }
    }
    
    public Image getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getXa() {
        return xa;
    }
    
    public void setPlayer(String src) {
        try {
            player = t.getImage("src/resources/Balmer/" + src);
        } catch (Error e)
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
			up = true;
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
			up = false;
		}
	}

    public void move() {
		// Base your acceleration on the Booleans set by your keyPressed method
		if (right) {
			xa = 2;
		}
		else if (left) {
			xa = -2;
		}

		x += xa;
		y += ya;
	}

    public void paint(Graphics g) {
        if (right) {
            setPlayer("walking.gif");
            g.drawImage(player, x, y, null);
        }
        else if (left) {
            setPlayer("walking.gif");
            g.drawImage(player, x + 83, y, -83, 132, null);
        } else {
            setPlayer("ideal.gif");
            g.drawImage(player, x, y, null);
        }       
    }
}