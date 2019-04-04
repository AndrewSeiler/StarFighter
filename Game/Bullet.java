package Game;

import javax.imageio.ImageIO;
import java.awt.*;

public class Bullet extends MovingObject {
	private Image image;

	Bullet(int posX, int posY, float velX, float velY, int width, int height) {
		super(posX, posY, velX, velY, width, height);
		
		try {
			image = ImageIO.read(getClass().getResource("/images/bullet.png"));
		}
		catch(Exception e) {}
	}

	public void move(float velX, float velY, int distX, int distY) {
        setPosX(getPosX() + (int)getVelX());
        setPosY(getPosY() + (int)getVelY());
	}
  
	public void draw(Graphics window) {
   	window.drawImage(image, getPosX(), getPosY(), getWidth(), getHeight(), null);
	}
}
