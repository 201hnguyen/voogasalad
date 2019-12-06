package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelStatusManager;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.List;

public class GameSceneObject {
    private final LevelStatusManager myLevelStatusManager;
    private final SpriteManager mySpriteManager;
    private final String myBackgroundPath;

    public GameSceneObject(String backgroundPath, LevelStatusManager levelStatusManager, SpriteManager spriteManager) {
        myLevelStatusManager = levelStatusManager;
        mySpriteManager = spriteManager;
        myBackgroundPath = backgroundPath;
    }

    public int getResources() {
        return myLevelStatusManager.getResources();
    }

    public int getLives() {
        return myLevelStatusManager.getLives();
    }

    public int getLevelScore() { return myLevelStatusManager.getScore(); }

    public List<Sprite> getOnScreenSprites() {
        return mySpriteManager.getOnScreenSprites() ;
    }

    public String getBackgroundPath() {
        return myBackgroundPath;
    }
}
