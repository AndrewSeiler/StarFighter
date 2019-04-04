package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class Ship extends MovingObject {
	private float speed;
	private Image image;
	AffineTransform identity = new AffineTransform();
  
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
		setSpeed(velX, velY);
		
		try {
			image = ImageIO.read(getClass().getResource("/images/ship.jpg"));
		}
		catch(Exception e) { }
	}
  
	private void setSpeed(float velX, float velY) {
    speed = (float)Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
	}

	public void move(float velX, float velY, int distX, int distY) {
        setSpeed(velX, velY);

		float distance = (float)Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
		float interval = Math.abs(distance / speed);
		float incX = (float)distX / interval;
		float incY = (float)distY / interval;

		for (int i = 0; i < distance; i += interval) {
		    setPosX(getPosX() + (int)incX);
            setPosY(getPosY() + (int)incY);
            if (getPosX() < 0) {
                setPosX(0);
                return;
            }
            if (getPosX() > 1000 - getWidth()) {
                setPosX(1000 - getWidth());
                return;
            }
            if (getPosY() < 0) {
                setPosY(0);
                return;
            }
            if (getPosY() > 632 - getHeight()) {
                setPosY(632 - getHeight());
                return;
            }
		}
	}
  
	public void draw(Graphics window, int rotation) {
	    Graphics2D g2d = (Graphics2D)window;
        AffineTransform transform = new AffineTransform();
        transform.setTransform(identity);
        transform.rotate(Math.toRadians(rotation));

   	    g2d.drawImage(image, transform, new ImageObse);
	}
}
