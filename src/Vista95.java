import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Vista95 extends JPanel {

	private Player p = new Player();
	private Enemy e = new Enemy(200, 200, 0, 100);
	private Level l = new Level();

	public Vista95() {

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Passes the KeyEvent e to the ball instance
				p.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// Passes the KeyEvent e to the ball instance
				p.keyPressed(e);
			}
		});

		setFocusable(true);
	}

	public void move() {
		// l.move(p.getX(), p.getXa());
		e.move();
		p.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		l.paint(g2d);
		
		e.paint(g2d);
		p.paint(g2d);
		
		
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Vista 95");
		Vista95 v95 = new Vista95();
		frame.add(v95);
		frame.setSize(1020, 640);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while (true) {
			v95.move();
			v95.repaint();
			Thread.sleep(10);
		}
	}
}