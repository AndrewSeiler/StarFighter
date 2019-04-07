package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends Canvas implements KeyListener, Runnable {
	private static final long serialVersionUID = -1L;
	private Ship ship;
	private Aliens aliens;
	private Bullets bullets;
	private PowerUps powerUps;
	private boolean[] keys;
	private BufferedImage back;
	private int shootTimer = 0;
	private int rotation = 270;
	private int level = 1;
	private int powerDuration = 0;

	private Thread thread;

	Game() {
		setBackground(Color.BLACK);
		keys = new boolean[7];

		ship = new Ship(480, 500, 0, 0, 40, 40);
		aliens = new Aliens();
		bullets = new Bullets();
		powerUps = new PowerUps();

		this.addKeyListener(this);
		thread = new Thread(this);
		thread.start();

		setVisible(true);
		loadAliens();
	}

	public void update(Graphics window) {
		if (shootTimer > 0) {
			shootTimer--;
		}
		if (powerDuration > 0) {
			powerDuration--;
		}
		if (aliens.getList().isEmpty()) {
			System.out.println(level);
			level++;
			aliens.resetCounter();
			ship.setPos(480, 500);
			loadAliens();
		}
		paint(window);
	}

	public void paint(Graphics window) {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		if (tlr.nextInt(0, 1000) <= 1) {
			int levelI = 1;
			int levelR = tlr.nextInt(0, 15);
			if (levelR > 12 - (level - 1) % 5)
				levelI = 3;
			else if (levelR > 8 - ((level - 1) * 2) % 5)
				levelI = 2;
			powerUps.add(new PowerUp(tlr.nextInt(0, 1990), -30, 0, 0.1F, 10, 10, tlr.nextInt(1, 4), levelI,
					tlr.nextInt(1000, 2000)));
		}

		Graphics2D twoDGraph = (Graphics2D) window;
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 1000, 632);
		graphToBack.setColor(Color.WHITE);
		graphToBack.drawString("Level " + level, 10, 20);
		graphToBack.drawString("PowerUp: " + (powerDuration / 2), 10, 40);

		float x = (float) Math.cos(Math.toRadians(rotation));
		float y = (float) Math.sin(Math.toRadians(rotation));
		if (keys[0]) {
			rotation--;
			rotation %= 360;
		} else if (keys[1]) {
			rotation++;
			rotation %= 360;
		}
		if (keys[2]) {
			ship.move(x, y, 1.5F);
		} else if (keys[3]) {
			ship.move(-x, -y, 1.5F);
		}
		if (keys[4] && shootTimer == 0) {
			Bullet bullet;
			if (powerUps.getActivated() != null) {
				switch (powerUps.getActivated().getType()) {
				case 1:
					shootTimer = 200;
					bullet = new Bullet(ship.getPosX() + (ship.getWidth() / 2.0F) - 1,
							ship.getPosY() + (ship.getHeight() / 2.0F) - 1, (float) Math.cos(Math.toRadians(rotation)),
							(float) Math.sin(Math.toRadians(rotation)), 4, 4, 1 + powerUps.getActivated().getLevel(), 1);
					bullets.add(bullet);
					break;
				case 2:
					shootTimer = 60 - (10 * powerUps.getActivated().getLevel());
					bullet = new Bullet(ship.getPosX() + (ship.getWidth() / 2.0F) - 1,
							ship.getPosY() + (ship.getHeight() / 2.0F) - 1, (float) Math.cos(Math.toRadians(rotation)),
							(float) Math.sin(Math.toRadians(rotation)), 4, 4, 1, 1);
					bullets.add(bullet);
					break;
				case 3:
					shootTimer = 300;
					int level = powerUps.getActivated().getLevel();
					for (int i = 0 - level; i < 1 + level; i++) {
						if (i == 0)
							bullet = new Bullet(ship.getPosX() + (ship.getWidth() / 2.0F) - 1,
									ship.getPosY() + (ship.getHeight() / 2.0F) - 1,
									(float) Math.cos(Math.toRadians(rotation)),
									(float) Math.sin(Math.toRadians(rotation)), 4, 4, 1, 1);
						else
							bullet = new Bullet(ship.getPosX() + (ship.getWidth() / 2.0F) - 1,
									ship.getPosY() + (ship.getHeight() / 2.0F) - 1,
									(float) Math.cos(Math.toRadians(rotation + 45.0 / i)),
									(float) Math.sin(Math.toRadians(rotation + 45.0 / i)), 4, 4, 1, 1);
						bullets.add(bullet);
					}
					break;
				}
			} else {
				shootTimer = 200;
				bullet = new Bullet(ship.getPosX() + (ship.getWidth() / 2.0F) - 1,
						ship.getPosY() + (ship.getHeight() / 2.0F) - 1, (float) Math.cos(Math.toRadians(rotation)),
						(float) Math.sin(Math.toRadians(rotation)), 4, 4, 1, 1);
				bullets.add(bullet);
			}
		}
		aliens.move();
		aliens.cleanUp();
		bullets.addList(aliens.shoot());
		bullets.move();
		bullets.cleanUp();
		powerUps.move();
		powerUps.cleanUp();

		if (aliens.over) {
			System.out.println("You Lose (Aliens Won)!");
			stop();
			System.exit(0);
			return;
		}
		ArrayList<Bullet> alienB = new ArrayList<Bullet>();
		ArrayList<Bullet> playerB = new ArrayList<Bullet>();
		for (Bullet bullet : bullets.getList()) {
			if (bullet.getType() == 1) playerB.add(bullet);
			else if (bullet.getType() == -1) alienB.add(bullet);
		}
		ArrayList<Bullet> tempB = new ArrayList<Bullet>();
		float sPX = ship.getPosX();
		float sPY = ship.getPosY();
		float sW = ship.getWidth();
		float sH = ship.getHeight();
		for (Alien alien : aliens.getList()) {
			float aPX = alien.getPosX();
			float aPY = alien.getPosY();
			float aW = alien.getWidth();
			float aH = alien.getHeight();
			for (Bullet bullet : playerB) {
				float bPX = bullet.getPosX();
				float bPY = bullet.getPosY();
				if (bPX - aPX >= 1 - bullet.getWidth() && bPX - aPX < aW && bPY - aPY >= 1 - bullet.getHeight() && bPY - aPY < aH) {
					alien.decHealth(bullet.getDamage());
					tempB.add(bullet);
				}
			}
			if (sPX - aPX >= 1 - ship.getWidth() && sPX - aPX < aW && sPY - aPY >= 1 - ship.getHeight() && sPY - aPY < aH) {
				System.out.println("You Lose (Hit by Alien)!");
				stop();
				System.exit(0);
				return;
			}
		}
		for (Bullet bullet : alienB) {
			float bPX = bullet.getPosX();
			float bPY = bullet.getPosY();
			if (bPX - sPX >= 1 - sW && bPX - sPX < sW && bPY - sPY >= 1 - sH && bPY - sPY < sH) {
				System.out.println("You Lose (Hit by Alien Bullet)!");
				stop();
				System.exit(0);
				return;
			}
			for (Bullet bullet2 : playerB) {
				float b2PX = bullet2.getPosX();
				float b2PY = bullet2.getPosY();
				int b2W = bullet2.getWidth();
				int b2H = bullet2.getHeight();
				if (bPX - b2PX >= 1 - b2W && bPX - b2PX < b2W && bPY - b2PY >= 1 - b2H && bPY - b2PY < b2H) {
					tempB.add(bullet);
					tempB.add(bullet2);
				}
			}
		}
		if (!tempB.isEmpty()) bullets.removeList(tempB);
		PowerUp tempP = null;
		for (PowerUp powerUp : powerUps.getList()) {
			float pPX = powerUp.getPosX();
			float pPY = powerUp.getPosY();
			float pW = powerUp.getWidth();
			float pH = powerUp.getHeight();
			if (sPX - pPX >= 1 - ship.getWidth() && sPX - pPX < pW && sPY - pPY >= 1 - ship.getHeight()
					&& sPY - pPY < pH) {
				powerUps.setActivated(powerUp);
				powerDuration = powerUp.getDuration();
				tempP = powerUp;
			}
		}
		if (tempP != null)
			powerUps.remove(tempP);
		aliens.draw(graphToBack);
		bullets.draw(graphToBack, rotation);
		powerUps.draw(graphToBack, rotation);
		ship.draw(graphToBack, rotation);

		twoDGraph.drawImage(back, null, 0, 0);

		if (powerDuration == 0)
			powerUps.setActivated(null);

	}

	private void loadAliens() {
		if (level % 5 == 0) {
			float health = level * 2;
			aliens.add(new Alien(450, 50, 0, 0.02F, 100, 100, (int) health));
		} else {
			int health = level % 5;
			for (int i = 0; i < level % 5; i++) {
				int size = (int) (10 * Math.ceil((health + 2) / 2.0F));
				for (int j = 0; j < 10; j++)
					aliens.add(new Alien((250 - (size / 2.0F)) + (j * 60), (float) (size + (i * 60)), 0,
							0.05F + (level / 100F), size, size, health));
				health--;
			}
		}
	}

	void stop() {
		thread = null;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			keys[0] = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			keys[1] = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			keys[2] = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			keys[3] = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			keys[4] = true;
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			keys[0] = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			keys[1] = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			keys[2] = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			keys[3] = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			keys[4] = false;
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
		} catch (Exception ignored) {
		}
	}
}