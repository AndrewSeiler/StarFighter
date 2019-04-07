package Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class PowerUps {
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private PowerUp activated;

	PowerUps() {
	}

	void add(PowerUp powerUp) {
		powerUps.add(powerUp);
	}

	void draw(Graphics window, int rotation) {
		for (PowerUp powerUp : powerUps) {
			powerUp.draw(window, rotation);
		}
	}

	void move() {
		for (PowerUp powerUp : powerUps) {
			powerUp.move(0, 0, 0);
		}
	}

	void cleanUp() {
		ArrayList<PowerUp> temp = new ArrayList<PowerUp>(powerUps);
		for (PowerUp powerUp : temp) {
			float posX = powerUp.getPosX();
			if (posX < 0 - powerUp.getWidth() || posX > 1000 || powerUp.getPosY() > 632) {
				powerUps.remove(powerUp);
			}
		}
	}

	void remove(PowerUp powerUp) {
		powerUps.remove(powerUp);
	}
	
	ArrayList<PowerUp> getList() {
		return powerUps;
	}

	void setActivated(PowerUp powerUp) { activated = powerUp; }
	PowerUp getActivated() { return activated; }
}