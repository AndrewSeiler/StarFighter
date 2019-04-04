package Game;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class Alien extends MovingObject {
	private Image image;
	private double counter = 0; 

	Alien(float posX, float posY, float velX, float velY, int width, int height) {
		super(posX, posY, velX, velY, width, height);

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
	
	public void move(float velX, float velY, float speed) {
		counter += .0025;
		setVelX((float)Math.sin(counter));
		setPosX(getPosX() + getVelX() / 2);
		setPosY(getPosY() + getVelY() / 2);
	}
	
 	public void draw(Graphics window, int rotation) {
		//Graphics2D g2d = (Graphics2D)window;
        //g2d.rotate(Math.toRadians(rotation + 90), getPosX() + (getWidth() / 2), getPosY() + (getHeight() / 2));
   	    //g2d.drawImage(image, (int)getPosX(), (int)getPosY(), getWidth(), getHeight(), null);
   	    //g2d.dispose();
		window.drawImage(image, (int)getPosX(), (int)getPosY(), getWidth(), getHeight(), null);
	}
}
