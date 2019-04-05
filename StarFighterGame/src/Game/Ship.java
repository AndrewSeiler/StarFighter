package Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Ship extends MovingObject {
	private Image image;

	public Ship() {
		this(10, 10, 10, 10, 10, 10);
	}

	public Ship(int posX, int posY) {
		this(posX, posY, 10, 10, 10, 10);
	}

	public Ship(int posX, int posY, int velX, int velY) {
		this(posX, posY, velX, velY, 10, 10);
	}

	public Ship(int posX, int posY, int velX, int velY, int width, int height) {
		super(posX, posY, velX, velY, width, height);

		try {
			image = ImageIO.read(getClass().getResource("/images/ship.jpg"));
		} catch (Exception e) {
		}
	}

	public void move(float velX, float velY, float speed) {

		setVelX(velX * speed);
		setVelY(velY * speed);
		
		setPos(getPosX() + getVelX(), getPosY() + getVelY());
		if (getPosX() < 0) {
			setPosX(0);
		}
		if (getPosX() > 1000 - getWidth()) {
			setPosX(1000 - getWidth());
		}
		if (getPosY() < 0) {
			setPosY(0);
		}
		if (getPosY() > 632 - getHeight() * 2) {
			setPosY(632 - getHeight() * 2);
		}
	}

	public void draw(Graphics window, int rotation) {
	    Graphics2D g2d = (Graphics2D)window;
        g2d.rotate(Math.toRadians(rotation + 90), getPosX() + (getWidth() / 2), getPosY() + (getHeight() / 2));
   	    g2d.drawImage(image, (int)getPosX(), (int)getPosY(), getWidth(), getHeight(), null);
   	    g2d.dispose();
	}
}
