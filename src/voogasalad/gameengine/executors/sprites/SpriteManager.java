package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

public interface SpriteManager {
    void addSpritePrototype(int prototypeId, Sprite sprite);
    void makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException;
    List<Sprite> getOnScreenSprites();
    List<Sprite> getSpritePrototypes();
    List<Sprite> getSpritesByArchetype(SpriteArchetype archetype);
    void removeSpriteById(int spriteId);
    }
