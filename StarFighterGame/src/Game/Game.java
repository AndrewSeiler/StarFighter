package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Game extends Canvas implements KeyListener, Runnable {
	private Ship ship;
	private Aliens aliens;
	private Bullets bullets;

	private boolean[] keys;
	private BufferedImage back;
	private int shootTimer = 0;
	private int rotation = 270;
	private int level = 4;
	
	private Thread thread;

	Game() {
		setBackground(Color.BLACK);

		keys = new boolean[7];

		ship = new Ship(480, 500, 0, 0, 40, 40);
		aliens = new Aliens();
		bullets = new Bullets();

		this.addKeyListener(this);
		thread = new Thread(this);
		thread.start();

		setVisible(true);
		loadAliens();
	}

	public void update(Graphics window) {
		paint(window);
		if (shootTimer > 0) {
			shootTimer--;
		}
	}

	public void paint(Graphics window) {
		Graphics2D twoDGraph = (Graphics2D) window;
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 1000, 632);
		float x = (float)Math.cos(Math.toRadians(rotation));
		float y = (float)Math.sin(Math.toRadians(rotation));
		if (keys[0]) {
			ship.move(y, -x, 0.01F);
		}
		else if (keys[1]) {
			ship.move(-y, x, 0.01F);
		}
		if (keys[2]) {
			ship.move(x, y, 1.5F);
		}
		else if (keys[3]) {
			ship.move(-x, -y, 1.5F);
		}
		if (!keys[0] && !keys[1] && !keys[2] && !keys[3]) {
			ship.move(x, y, 0.1F);
		}
		if (keys[5]) {
			rotation--;
			rotation %= 360;
		}
		else if (keys[6]) {
			rotation++;
			rotation %= 360;
		}
		if (keys[4] && shootTimer == 0) {
			shootTimer = 200;
			float c = (float) Math.cos(Math.toRadians(rotation));
			float d = (float) Math.sin(Math.toRadians(rotation));
			Bullet bullet = new Bullet(ship.getPosX() + (ship.getWidth() / 2) - 1, ship.getPosY() + (ship.getHeight() / 2) - 1, c, d, 2, 2);
			bullets.add(bullet);
		}
		aliens.move();
		aliens.cleanUp();
		bullets.move();
		bullets.cleanUp();
		
		if (aliens.over) {
			System.out.println("You Lose!");
			stop();
			return;
		}
		
		//collision detection
		ArrayList<Alien> tempA = new ArrayList<Alien>();
		ArrayList<Bullet> tempB = new ArrayList<Bullet>();
		float sPX = ship.getPosX();
		float sPY = ship.getPosY();
		for (Alien alien : aliens.getList()) {
			float aPX = alien.getPosX();
			float aPY = alien.getPosY();
			for (Bullet bullet : bullets.getList()) {
				float bPX = bullet.getPosX();
				float bPY = bullet.getPosY();
				if (bPX - aPX >= 1 - bullet.getWidth() && bPX - aPX < 31) {
					if (bPY - aPY >= 1 - bullet.getHeight() && bPY - aPY < 30) {
						tempA.add(alien);
						tempB.add(bullet);
					}
				}
			}
			if (sPX - aPX >= 1 - ship.getWidth() && sPX - aPX < 31) {
				if (sPY - aPY >= 1 - ship.getHeight() && sPY - aPY < 30) {
					System.out.println("You Lose!");
					stop();
					return;
				}
			}
		}
		for (Alien alien : tempA) {
			aliens.remove(alien);
		}
		for (Bullet bullet : tempB) {
			bullets.remove(bullet);
		}
		
		
		
		aliens.draw(graphToBack, 270);
		bullets.draw(graphToBack, rotation);
		ship.draw(graphToBack, rotation);
		
		
		twoDGraph.drawImage(back, null, 0, 0);
		
		if (aliens.getList().size() == 0) {
			System.out.println("You Win!");
			stop();
		}
		
	}
	
	private void loadAliens() {
		for (int i = 0; i < level * 10; i++) {
			Alien alien = new Alien(10 + ((i % 10) * 60), (float)(10 + (Math.floor(i / 10) * 60)), 0, 0.05F, 30, 30);
			aliens.add(alien);
		}
	}
	
	void stop() {
		thread = null;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		if (e.getKeyChar() == 'a') {
			keys[5] = true;
		}
		if (e.getKeyChar() == 's') {
			keys[6] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		if (e.getKeyChar() == 'a') {
			keys[5] = false;
		}
		if (e.getKeyChar() == 's') {
			keys[6] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void run() {
		try {
			Thread thisThread = Thread.currentThread();
			while (thread == thisThread) {
				Thread.sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}
}
