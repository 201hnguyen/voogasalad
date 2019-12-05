package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.List;

public class GameSceneObject {
    private final int myResources;
    private final int myLives;
    private final SpriteManager mySpriteManager;
    private final String myBackgroundPath;

    public GameSceneObject(String backgroundPath, int resources, int lives, SpriteManager spriteManager) {
        myResources = resources;
        myLives = lives;
        mySpriteManager = spriteManager;
        myBackgroundPath = backgroundPath;
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

    public String getBackgroundPath() {
        return myBackgroundPath;
    }
}
