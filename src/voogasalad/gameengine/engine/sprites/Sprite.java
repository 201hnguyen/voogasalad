package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

public interface Sprite {
    Sprite makeClone(int x, int y, int spriteId) throws GameEngineException;
    int getX();
    int getY();
    int getId();
    int getHealth();
}
