package Game;

import java.awt.Graphics;
import java.util.ArrayList;

class Bullets {
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	Bullets() {
	}

	void add(Bullet bullet) {
		bullets.add(bullet);
	}
	void addList(ArrayList<Bullet> list) {
		for (Bullet bullet : list) {
			this.bullets.add(bullet);
		}
	}

	void draw(Graphics window, int rotation) {
		for (Bullet bullet : bullets) {
			bullet.draw(window, rotation);
		}
	}

	void move() {
		for (Bullet bullet : bullets) {
			bullet.move(0, 0, 0);
		}
	}

	void cleanUp() {
		ArrayList<Bullet> temp = new ArrayList<Bullet>(bullets);
		for (Bullet bullet : temp) {
			float posX = bullet.getPosX();
			float posY = bullet.getPosY();
			if (posX < 0 - bullet.getWidth() || posX > 1000 || posY < 0 - bullet.getHeight() || posY > 632) {
				bullets.remove(bullet);
			}
		}
	}

	void remove(Bullet bullet) {
		bullets.remove(bullet);
	}
	void removeList(ArrayList<Bullet> list) {
		for (Bullet bullet : list) {
			bullets.remove(bullet);
		}
	}

	ArrayList<Bullet> getList() {
		return bullets;
	}
}