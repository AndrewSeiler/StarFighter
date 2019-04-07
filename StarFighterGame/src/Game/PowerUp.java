package Game;

import javax.imageio.ImageIO;
import java.awt.*;

public class PowerUp extends MovingObject {
	private Image image;
	private int type;
	private int level;
	private int duration;

	PowerUp(float posX, float posY, float velX, float velY, int width, int height, int type, int level, int duration) {
		super(posX, posY, velX, velY, width, height);
		this.type = type;
		this.level = level;
		this.duration = duration;
		
		try {
			image = ImageIO.read(getClass().getResource("/images/powerUp" + type + "-" + level + ".png"));
		} catch (Exception ignored) {
		}
	}
	
	
	public void setType(int type) {
		this.type = type;
	}
	int getType() {
		return type;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	int getLevel() { return level; }

	public void setDuration(int duration) { this.duration = duration; }
	public int getDuration() { return duration; }
	
	public void move(float velX, float velY, float speed) { setPos(getPosX() + getVelX(), getPosY() + getVelY()); }
	
	public void draw(Graphics window, int rotation) { window.drawImage(image, (int)getPosX(), (int)getPosY(), getWidth(), getHeight(), null); }
}