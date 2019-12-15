package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelStatusManager;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.List;

/**
 * Class: GameSceneObject
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class GameSceneObject {
    private final LevelStatusManager myLevelStatusManager;
    private final SpriteManager mySpriteManager;
    private final String myBackgroundPath;

    /**
     * Purpose:
     * Assumptions:
     * @param backgroundPath
     * @param levelStatusManager
     * @param spriteManager
     */
    public GameSceneObject(String backgroundPath, LevelStatusManager levelStatusManager, SpriteManager spriteManager) {
        myLevelStatusManager = levelStatusManager;
        mySpriteManager = spriteManager;
        myBackgroundPath = backgroundPath;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getResources() {
        return myLevelStatusManager.getResources();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getLives() {
        return myLevelStatusManager.getLives();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getLevelScore() { return myLevelStatusManager.getScore(); }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public List<Sprite> getOnScreenSprites() {
        return mySpriteManager.getCopyOnScreenSprites() ;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getBackgroundPath() {
        return myBackgroundPath;
    }
}
