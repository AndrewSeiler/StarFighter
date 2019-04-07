package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class Alien extends MovingObject {
	private Image image;
	private double counter = 0;
	private int health;

	Alien(float posX, float posY, float velX, float velY, int width, int height, int health) {
		super(posX, posY, velX, velY, width, height);
		this.health = health;
		try {
			image = ImageIO.read(getClass().getResource("/images/alien.jpg"));
		} catch (Exception ignored) {
		}
	}

	void setCounter(double inc) {
		counter = inc;
	}
	
	public Bullet shoot() {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		return new Bullet(getPosX(), getPosY(), (float)Math.cos(Math.toRadians(90 + tlr.nextInt(-5, 5))), (float)(getVelY() + tlr.nextDouble(.5, 1)), 4, 4, 1, -1);
	}

	public double getCounter() {
		return counter;
	}

	void decHealth(int dec) {
		this.health -= dec;
	}

	int getHealth() {
		return health;
	}

	public void move(float velX, float velY, float speed) {
		counter += .0025 + (.0005 * (getWidth() / 10));
		setVelX((float) Math.sin(counter));
		setPos(getPosX() + getVelX() / 2, getPosY() + getVelY() / 2);
	}

	public void draw(Graphics window, int rotation) {
		window.drawImage(image, (int) getPosX(), (int) getPosY(), getWidth(), getHeight(), null);
	}
}