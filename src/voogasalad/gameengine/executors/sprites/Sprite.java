package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

public interface Sprite {
    Sprite makeClone(double x, double y, int spriteId) throws GameEngineException;
    double getX();
    double getY();
    int getId();
    int getHealth();
    SpriteArchetype getSpriteArchetype();
    Object getImage();
    void updatePosition(double elapsedTime);
    String getImagePath();
    }
