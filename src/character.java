import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class character {

    private BufferedImage w1 = null;
    private BufferedImage w2 = null;
    private BufferedImage w3 = null;
    private BufferedImage w4 = null;
    private BufferedImage w5 = null;
    private BufferedImage j1 = null;
    private BufferedImage j2 = null;
    private BufferedImage j3 = null;
    private BufferedImage j4 = null;
    private BufferedImage idle = null;
    private BufferedImage heart = null;

    private BufferedImage img = null;

    int x = 50, y = 100, width = 95, height = 130;
    int xa = 3;
    // int baseY = 200;
    boolean inMargin = true;
    boolean left = false, right = false, up = false;;
    boolean gravity = true;
    private int hearts = 3;
    boolean isAlive = true;
    // boolean top = false;
    int jump = 0;
    boolean isGround = false;
    boolean isIdle = true;

    Toolkit t = Toolkit.getDefaultToolkit();
    private Image player = null;

    public void restart() {
        this.x = 50;
        this.y = 100;
    }

    public void setAlive() {
        isAlive = true;
        hearts = 3;
    }

    public int getYH() {
        return y + height;
    }

    public void loseLife() {
        hearts--;
    }

    public void setLife(int heart) {
        this.hearts = heart;
    }

    public character(int hearts) {
        try {
            j1 = ImageIO.read(new File("src/resources/jump1.png"));
            j2 = ImageIO.read(new File("src/resources/jump2.png"));
            j3 = ImageIO.read(new File("src/resources/jump3.png"));
            j4 = ImageIO.read(new File("src/resources/jump4.png"));
            idle = ImageIO.read(new File("src/resources/idle.png"));
            heart = ImageIO.read(new File("src/resources/heart.png"));
        } catch (IOException e) {
            System.out.println("No Image");
        }
        this.hearts = hearts;
    }

    public void keyPressed(KeyEvent e) {
        // This checks to see which key was pressed, sets boolean to true
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
            // end = start - 60;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
            // end = start + 60;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        // When the key is released, set the Boolean to false, and change
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public boolean move(background b) {

        if (isAlive) {
            gravity = !b.list.collision(this);
            if (!gravity)
                isGround = true;
            // once character touches ground they can jump
            if (left || right) {
                // within margins and limits
                if (((x <= b.x + 510 && x > b.x) || (x <= b.x + b.width && x > b.x + b.width - 510)) && left) {
                    x -= xa;
                    b.margin = true;
                } else if (((x < b.x + 510 && x >= b.x) || (x < b.x + b.width && x >= b.x + b.width - 510)) && right) {
                    x += xa;
                    b.margin = true;
                } else {
                    b.margin = false;
                }
            }
            // If gravity is on then player falls
            if (gravity) {
                y += 4;
            }
            if (up && isGround) {// prevents doubleJump/flying
                if (jump < 150) {
                    y -= 9;
                    jump += 5;
                } else {
                    jump = 0;
                    up = false;
                    isGround = false;
                }
            }
            // Player dies if they fall into the void
            if (y >= 640 || hearts == 0) {
                isAlive = false;
            }
            // Updates isIdle boolean
            isIdle = !right && !left;
        }

        return !gravity;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 10, y, 83 - 20, 132);
    }

    public void setPlayer(String src) {
        try {
            // player = t.getImage("src/resources/Balmer/" + src);
            player = t.getImage(src);
        } catch (Error e) {
            System.out.println("No Image");
        }
    }

    public void paint(Graphics2D g) {
        if (up && (left || right)) {
            if (jump <= 60 && jump >= 0) {
                setImg(j1);
            } else if (jump <= 130 && jump >= 60) {
                setImg(j2);
            } else if (jump <= 200 && jump >= 130) {
                setImg(j3);
            }

        } else if (left || right) {
            setPlayer("src/resources/walking.gif");
        }

        if (isIdle) {
            setImg(idle);
            g.drawImage(img, x, y, width, height, null);
        } else if (left) {
            // 20 pixel difference between the images and gif - prevent distortion
            if (up)
                g.drawImage(img, x + width, y, -width, height, null);
            else
                g.drawImage(player, x + width - 20, y, -(width - 20), height, null);
        } else {
            if (up)
                g.drawImage(img, x, y, width, height, null);
            else
                g.drawImage(player, x, y, width - 20, height, null);
        }

        if (gravity) {
            g.setColor(Color.RED);
            // g.drawRect(x,y,width,height);
        }
        for (int i = 1; i <= hearts; i++) {
            g.drawImage(heart, -20 + (i * 50), 35, 40, 40, null);
        }
        // g.drawRect(x+10, y, 63, height);
        // g.drawLine(x-10, y+height, x+width+10, y+height);
        // System.out.println(y+height);

    }
}