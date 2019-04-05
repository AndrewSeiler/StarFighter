package Game;

public interface Movable {
	void setPos(float posX, float posY);

	void setPosX(float posX);

	void setPosY(float posY);

	float getPosX();

	float getPosY();

	int getWidth();

	int getHeight();

	void setWidth(int width);

	void setHeight(int height);

	void setVelX(float velX);

	void setVelY(float velY);

	float getVelX();

	float getVelY();
}
