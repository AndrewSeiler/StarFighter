package Game;

import java.awt.Graphics;

public abstract class MovingObject implements Movable {
	private float posX;
	private float posY;
	private int width;
	private int height;
	private float velX;
	private float velY;

	MovingObject(float posX, float posY, float velX, float velY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
	}

	public void setPos(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public abstract void move(float velX, float velY, float speed);

	public abstract void draw(Graphics window, int rotation);
}