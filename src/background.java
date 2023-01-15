import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class background {
	private BufferedImage img = null;
	int x, y, speed = 10;
	int width, height = 640;
	boolean margin = true;
	boolean left, right;
	int stage;
	private BufferedImage gameOver = null;
	private BufferedImage levelUp = null;

	Enemy[] elist = new Enemy[3];
	{
		elist[0] = new Enemy(300, 572, 0, 100);
		elist[1] = new Enemy(400, 572, 0, 100);
		elist[2] = new Enemy(500, 572, 0, 100);
	}

	LineList list = new LineList();

	public background(int s) {
		/*
		 * 1. Start menu
		 * 2. Instruction menu
		 * 3. Level 1
		 * 4. Level 2
		 * 5. level 3
		 * 6. Ending credits
		 */
		stage = s;
		try {
			gameOver = ImageIO.read(new File("src/resources/gameOver.png"));
			levelUp = ImageIO.read(new File("src/resources/levelUp.png"));

			if (stage == 1)
				img = ImageIO.read(new File("src/resources/start.png"));
			else if (stage == 2)
				img = ImageIO.read(new File("src/resources/instructions.png"));
			else if (stage == 3)
				img = ImageIO.read(new File("src/resources/level1.png"));
			else if (stage == 4)
				img = ImageIO.read(new File("src/resources/level2.png"));
			else if (stage == 5)
				img = ImageIO.read(new File("src/resources/level3.png"));
			else if (stage == 6)
				img = ImageIO.read(new File("src/resources/end.png"));

		} catch (IOException e) {
			System.out.println("No Image");
		}

		if (stage == 3 || stage == 4 || stage == 5) {
			this.width = 5100;
			try {
				FileReader fr;
				if (stage == 3) {
					fr = new FileReader("src/resources/LINES level 1.txt");
				} else if (stage == 4) {
					fr = new FileReader("src/resources/LINES level 2.txt");
				} else {
					fr = new FileReader("src/resources/LINES level 3.txt");
				}
				BufferedReader br = new BufferedReader(fr);

				String line;
				while ((line = br.readLine()) != null) {
					String[] part = line.split(" ");
					lines temp = new lines(Integer.parseInt(part[0]), Integer.parseInt(part[1]),
							Integer.parseInt(part[2]));
					list.addLine(temp);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("ERROR");
			}
		} else
			this.width = 1020;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
	}

	public void move(character c) {
		if (c.left && !margin && this.x < 0) {
			x += speed;
			list.moveLeft(this.speed);
		}
		if (c.right && !margin && this.x + this.width > 1020) {
			x -= speed;
			list.moveRight(this.speed);
		}
	}

	// use for error checking, background moves with keypress not player
	public void move() {
		if (left) {
			x += speed;
			list.moveLeft(this.speed);
		}
		if (right) {
			x -= speed;
			list.moveRight(this.speed);
		}
	}

	public void paint(Graphics2D g) {
		g.drawImage(img, x, y, width, height, null);
		g.setColor(Color.RED);
		list.paint(g);
	}

	public void gameOver(Graphics2D g) {
		Color c = new Color(0, 0, 0, 80);
		g.setColor(c);
		g.fillRect(0, 0, 1020, 640);
		g.drawImage(gameOver, 85, 50, 850, 540, null);
	}

	public void levelUp(Graphics2D g) {
		Color c = new Color(0, 0, 0, 80);
		g.setColor(c);
		g.fillRect(0, 0, 1020, 640);
		g.drawImage(levelUp, 85, 50, 850, 540, null);
	}
}