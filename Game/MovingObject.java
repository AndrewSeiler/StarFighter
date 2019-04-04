package Game;

import java.awt.*;

public abstract class MovingObject implements Movable
{
	private int posX;
	private int posY;
	private int width;
	private int height;
	private float velX;
	private float velY;
  
	MovingObject(int posX, int posY, float velX, float velY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
	}
  
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
  
	public void setPosX(int posX) {
		this.posX = posX;
	}
  
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getPosX() {
		return posX;
	}
  
	public int getPosY() {
		return posY;
	}
  
    public void setVelX(int velX) {
    this.velX = velX;
  }
  
    public void setVelY(int velY) {
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
  
	public abstract void move(float velX, float velY, int distX, int distY);
	public abstract void draw(Graphics window, int rotation);
}