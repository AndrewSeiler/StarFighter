package Game;

import java.awt.*;
import java.util.ArrayList;

public class Bullets {
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	public Bullets() {
	}

	public void add(Bullet bullet) {
	    bullets.add(bullet);
	}

	public void draw(Graphics window) {
	    for (Bullet bullet : bullets) {
	        bullet.draw(window);
	    }
	}

	public void move() {
	    for (Bullet bullet : bullets) {
	        bullet.move(0, 0, 0, 0);
	    }
	}

	void cleanUp() {
	    ArrayList<Bullet> temp = new ArrayList<Bullet>(bullets);
	    for (Bullet bullet : temp) {
	        int posX = bullet.getPosX();
	        int posY = bullet.getPosY();
	        int width = bullet.getWidth();
	        int height = bullet.getHeight();
	        if (posX < 0 - width || posX > 1000 || posY < 0 - height || posY > 632) {
	            bullets.remove(bullet);
            }
        }
	}
	public ArrayList<Bullet> getList() {
	    return bullets;
    }
}
