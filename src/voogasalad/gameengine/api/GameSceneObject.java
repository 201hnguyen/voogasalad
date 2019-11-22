package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

public class GameSceneObject {
    private final int myResources;
    private final int myLives;
    private final SpriteManager mySpriteManager;

    public GameSceneObject(int resources, int lives, SpriteManager spriteManager) {
        myResources = resources;
        myLives = lives;
        mySpriteManager = spriteManager;
    }

    public int getResources() {
        return myResources;
    }

    public int getLives() {
        return myLives;
    }

    public List<Sprite> getOnScreenSprites() {
        return mySpriteManager.getOnScreenSprites() ;
    }

    public List<Sprite> getSpritesByArchetype(SpriteArchetype archetype) { return mySpriteManager.getSpritesByArchetype(archetype); }
}
