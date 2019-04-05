package Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Aliens {
	private ArrayList<Alien> aliens = new ArrayList<Alien>();
	public boolean over;
	public double counter = Math.PI / 2;
	
	public Aliens() {
	}

	public void add(Alien alien) {
		aliens.add(alien);
		alien.setCounter(counter);
		if (aliens.size() % 10 == 0 && aliens.size() != 1) counter += .05;
	}

	public void draw(Graphics window, int rotation) {
		for (Alien alien : aliens) {
			alien.draw(window, rotation);
		}
	}

	public void move() {
		for (Alien alien : aliens) {
			alien.move(0, 0, 0);
		}
	}
	
	public void remove(Alien alien) {
		aliens.remove(alien);
	}
	
	public void cleanUp() {
		ArrayList<Alien> temp = new ArrayList<Alien>(aliens);
		for (Alien alien : temp) {
			float posX = alien.getPosX();
			float posY = alien.getPosY();
			int width = alien.getWidth();
			int height = alien.getHeight();
			if (posX <= 0 || posX > 1000 - width || posY > 632 - height) {
				over = true;
			}
			if (alien.getHealth() <= 0) {
				aliens.remove(alien);
			}
		}
	}

	public ArrayList<Alien> getList() {
		return aliens;
	}
}
