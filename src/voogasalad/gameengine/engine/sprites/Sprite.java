package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

public interface Sprite {
    Sprite makeClone(double x, double y, int spriteId) throws GameEngineException;
    double getX();
    double getY();
    int getId();
    int getHealth();
    Object getImage();
    void updatePosition(double elapsedTime);
    String getImagePath();
    }
