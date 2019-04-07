package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Aliens {
	private ArrayList<Alien> aliens = new ArrayList<Alien>();
	boolean over;
	private double counter = Math.PI / 2;
	Aliens() {
	}

	void add(Alien alien) {
		aliens.add(alien);
		alien.setCounter(counter);
		if (aliens.size() % 10 == 0 && aliens.size() != 1)
			counter += .1;
	}

	void draw(Graphics window) {
		for (Alien alien : aliens) {
			alien.draw(window, 270);
		}
	}

	void move() {
		for (Alien alien : aliens) {
			alien.move(0, 0, 0);
		}
	}

	ArrayList<Bullet> shoot() {
		ArrayList<Bullet> shots = new ArrayList<Bullet>();
		for (Alien alien : aliens) {
			ThreadLocalRandom tlr = ThreadLocalRandom.current();
			if (tlr.nextInt(0, 10000) < 5) {
				shots.add(alien.shoot());
			}
		}
		return shots;
	}
	
	public void remove(Alien alien) {
		aliens.remove(alien);
	}

	void cleanUp() {
		ArrayList<Alien> temp = new ArrayList<Alien>(aliens);
		for (Alien alien : temp) {
			if (alien.getPosY() > 632) {
				over = true;
			}
			if (alien.getHealth() <= 0) {
				aliens.remove(alien);
			}
		}
	}

	void resetCounter() {
		counter = Math.PI / 2;
	}

	ArrayList<Alien> getList() {
		return aliens;
	}
}