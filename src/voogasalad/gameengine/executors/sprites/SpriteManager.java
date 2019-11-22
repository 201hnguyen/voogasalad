package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.List;

public interface SpriteManager {
    void addSpritePrototype(int prototypeId, Sprite sprite);
    void makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException;
    List<Sprite> getOnScreenSprites();
    List<Sprite> getSpritePrototypes();
    void removeSpriteById(int spriteId);
    }
