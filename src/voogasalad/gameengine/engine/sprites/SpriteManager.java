package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.List;

public interface SpriteManager {
    void addSpritePrototype(int prototypeId, Sprite sprite);
    void makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException;
    List<JavaFXSprite> getOnScreenSprites();
}
