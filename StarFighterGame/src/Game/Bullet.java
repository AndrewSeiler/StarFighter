package Game;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Bullet extends MovingObject {
	private Image image;

	Bullet(float posX, float posY, float velX, float velY, int width, int height) {
		super(posX, posY, velX, velY, width, height);

		try {
			image = ImageIO.read(getClass().getResource("/images/bullet.png"));
		} catch (Exception e) {
		}
	}

	public void move(float velX, float velY, float speed) {
		setPos(getPosX() + getVelX(), getPosY() + getVelY());
	}

	public void draw(Graphics window, int rotation) {
		window.drawImage(image, (int)getPosX(), (int)getPosY(), getWidth(), getHeight(), null);
	}
}
