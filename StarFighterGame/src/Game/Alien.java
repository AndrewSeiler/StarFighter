package Game;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Alien extends MovingObject {
	private Image image;
	private double counter = 0;
	private int health;
	private int randNum;

	Alien(float posX, float posY, float velX, float velY, int width, int height, int health, int randNum) {
		super(posX, posY, velX, velY, width, height);
		this.health = health;
		this.randNum = randNum;

		try {
			image = ImageIO.read(getClass().getResource("/images/alien.jpg"));
		} catch (Exception e) {
		}
	}
	
	public void setCounter(double inc) {
		counter = inc;
	}
	public double getCounter() {
		return counter;
	}
	
	public void decHealth(int dec) {
		this.health -= dec;
	}
	public int getHealth() {
		return health;
	}
	
	public void move(float velX, float velY, float speed) {
		counter += .0025 + (.0005 * randNum);
		setVelX((float)Math.sin(counter));
		setPos(getPosX() + getVelX() / 2, getPosY() + getVelY() / 2);
	}
	
 	public void draw(Graphics window, int rotation) {
		window.drawImage(image, (int)getPosX(), (int)getPosY(), getWidth(), getHeight(), null);
	}
}
