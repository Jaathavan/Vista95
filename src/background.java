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

	Enemy[] elist1 = new Enemy[18];
	{
		elist1[0] = new Enemy(0, -572, 0, 5100-70);
		elist1[1] = new Enemy(0, 572, 1 ,2174-70);
		elist1[2] = new Enemy(623, 475, 2, 326-70);
		elist1[3] = new Enemy(756, 379,0,  326-70);
		elist1[4] = new Enemy(1278, 343, 1, 160-70);
		elist1[5] = new Enemy(1559, 479, 2, 272-70);
		elist1[6] = new Enemy(2321, 572, 0, 916-70);
		elist1[7] = new Enemy(2435, 515, 1, 117-70);
		elist1[8] = new Enemy(2554, 435, 2, 304-70);
		elist1[9] = new Enemy(2725, 362, 0, 369-70);
		elist1[10] = new Enemy(3373, 572, 1, 850-70);
		elist1[11] = new Enemy(3373, 468, 2, 304-70);
		elist1[12] = new Enemy(3677, 517, 0, 103-70);
		elist1[13] = new Enemy(3875, 444, 1, 160-70);
		elist1[14] = new Enemy(4190, 392, 2, 160-70);
		elist1[15] = new Enemy(4419, 572, 0, 682-70);
		elist1[16] = new Enemy(4458, 468, 1, 302-70);
		elist1[17] = new Enemy(4627, 402, 2, 302-70);
	}

	Enemy[] elist = elist1;

	Enemy[] elist2 = new Enemy[18];
	{
		elist2[0] = new Enemy(0, 432, 0, 356-70);
		elist2[1] = new Enemy(437, 352, 1, 154-70);
		elist2[2] = new Enemy(668, 361, 2, 525-70);
		elist2[3] = new Enemy(1194, 409, 0, 62-70);
		elist2[4] = new Enemy(1256, 453, 1, 91-70);
		elist2[5] = new Enemy(1597, 535, 2, 122-70);
		elist2[6] = new Enemy(1717, 486, 0, 98-70);
		elist2[7] = new Enemy(1812, 436, 1, 377-70);
		elist2[8] = new Enemy(2347, 582, 2, 274-70);
		elist2[9] = new Enemy(2621, 526, 1, 140-70);
		elist2[10] = new Enemy(2761, 456, 2, 135-70);
		elist2[11] = new Enemy(3000, 407, 0, 156-70);
		elist2[12] = new Enemy(3216, 293, 1, 255-70);
		elist2[13] = new Enemy(3682, 417, 2, 200-70);
		elist2[14] = new Enemy(3962, 345, 0, 122-70);
		elist2[15] = new Enemy(4185, 443, 1, 412-70);
		elist2[16] = new Enemy(4724, 290, 2, 367-70);
		elist2[17] = new Enemy(0, -572, 0, 5100-70);
	}

	Enemy[] elist3 = new Enemy[18];
	{
		elist3[0] = new Enemy(38, 424, 0, 192-70);
		elist3[1] = new Enemy(323, 496, 1, 310-70);
		elist3[2] = new Enemy(780, 437, 2, 170-70);
		elist3[3] = new Enemy(885, 218, 0, 178-70);
		elist3[4] = new Enemy(1062, 385, 1, 133-70);
		elist3[5] = new Enemy(1245, 282, 2, 156-70);
		elist3[6] = new Enemy(1538, 217, 0, 200-70);
		elist3[7] = new Enemy(1627, 420, 1, 230-70);
		elist3[8] = new Enemy(2023, 356, 2, 192-70);
		elist3[9] = new Enemy(2725, 362, 0, 369-70);
		elist3[10] = new Enemy(2350, 320, 1, 132-70);
		elist3[11] = new Enemy(2404, 515, 2, 260-70);
		elist3[12] = new Enemy(2673, 297, 0, 306-70);
		elist3[13] = new Enemy(3131, 454, 1, 160-70);
		elist3[14] = new Enemy(3514, 176, 2, 224-70);
		elist3[15] = new Enemy(4036, 575, 0, 494-70);
		elist3[16] = new Enemy(4268, 276, 1, 185-70);
		elist3[17] = new Enemy(4604, 216, 2, 192-70);
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
			else if (stage == 3) {
				img = ImageIO.read(new File("src/resources/level1.png"));
				elist = elist1;
			}
			else if (stage == 4) {
				img = ImageIO.read(new File("src/resources/level2.png"));
				elist = elist2;
			}
			else if (stage == 5) {
				img = ImageIO.read(new File("src/resources/level3.png"));
				elist = elist3;
			}
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
			list.moveLeft(speed);
			for (Enemy e : elist) {
				e.moveLeft(speed);
			}
		}
		if (c.right && !margin && this.x + this.width > 1020) {
			x -= speed;
			list.moveRight(speed);
			for (Enemy e : elist) {
				e.moveRight(speed);
			}
		}
	}

	// use for error checking, background moves with keypress not player
	public void move() {
		if (left) {
			x += speed;
			list.moveLeft(this.speed);
			for (Enemy e : elist) {
				e.moveLeft(speed);
			}
		}
		if (right) {
			x -= speed;
			list.moveRight(this.speed);
			for (Enemy e : elist) {
				e.moveRight(speed);
			}
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