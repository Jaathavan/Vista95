import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel {
	private background bg = new background(1);
	lines l = new lines(0, 800, 5100);
	character steve = new character(3);

	boolean restart = false;
	boolean nextLevel = true;

	// checkCollision variables
	boolean isBack = false;// can only lose one life per collision
	int temp = 0;// holds index of enemy that player collided with

	public GameScreen() {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (bg.stage == 3 || bg.stage == 4 || bg.stage == 5)
					steve.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (restart) { // if player died, restart the game
						restart = false;
						bg = new background(1);
						nextLevel = true;
						steve.setAlive();
					} else if (nextLevel) {
						bg.stage++; // continue on to the next stage
						bg = new background(bg.stage);
						if (bg.stage == 3 || bg.stage == 4 || bg.stage == 5)
							nextLevel = false;
						// if game stage, must restart character position
						steve.restart();
					}
				}
				if (bg.stage == 3 || bg.stage == 4 || bg.stage == 5)
					steve.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	public void checkCollision() {
		Rectangle pr = steve.getBounds();
		int count = 0;
		for (Enemy e : bg.elist) {
			if (pr.intersects(e.getBounds()) && (steve.getYH() >= e.getY() && steve.getYH() <= e.getY() + 10)) {
				e.die();
				// kill enemy
			} else if (pr.intersects(e.getBounds())) {
				if (!isBack) {
					steve.loseLife();// player loses a heart
				}
				isBack = true;
				temp = count; // holds index of hitbox player hit
			} else if (!pr.intersects(e.getBounds()) && temp == count) {
				isBack = false; // once player leaves collision box
			}
			count++;
		}
	}

	private void move() {
		if (!nextLevel) {
			bg.move(steve);
			if (bg.stage == 3 || bg.stage == 4 || bg.stage == 5)
				steve.move(bg);
			checkCollision();
			for (Enemy e : bg.elist) {
				e.move();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bg.paint(g2d);

		if (bg.stage == 3 || bg.stage == 4 || bg.stage == 5) {
			steve.paint(g2d);
			for (Enemy e : bg.elist) {
				e.paint(g2d);
			}
			if (steve.x >= 1020) {
				bg.levelUp(g2d);
				nextLevel = true;

			}
			if (!steve.isAlive) {
				bg.gameOver(g2d);
				restart = true;
			}
		}
		l.paint(g2d);
	}

	public static void main(String[] args) throws InterruptedException {

		JFrame f = new JFrame("Vista95");
		GameScreen s = new GameScreen();
		f.add(s);

		f.setSize(1020, 640);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			s.move(); // Updates the coordinates
			s.repaint(); // Calls the paint method
			Thread.sleep(10); // Pauses for a moment
		}

	}

}