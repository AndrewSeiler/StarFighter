package Game;
public interface Movable {
    void setPos(int posX, int posY);
    void setPosX(int posX);
    void setPosY(int posY);
    int getPosX();
    int getPosY();

    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);

    void setVelX(int velX);
    void setVelY(int velY);
    float getVelX();
    float getVelY();
}
