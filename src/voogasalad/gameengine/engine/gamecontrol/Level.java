package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.sprites.SpriteManager;

import java.awt.Point;
import java.util.List;
import java.util.Queue;

public class Level {

    SpriteManager mySpriteManager;
    Queue<Integer> mySpritesQueue; //TODO: this may get changed later for waves, but currently, let's simplify with this.
    Point mySpawnPoint; //TODO: this may also get changed/moved to handle waves
    double myElapsedTime;
    List<LevelCondition> myLevelConditions;

    public Level(SpriteManager spriteManager, Queue<Integer> spritesQueue, Point spawnPoint, List<LevelCondition> levelConditions) throws GameEngineException {
        mySpriteManager = spriteManager;
        mySpritesQueue = spritesQueue;
        mySpawnPoint = spawnPoint;
        myElapsedTime = 0;
        myLevelConditions = levelConditions;
    }

    public void execute(double elapsedTime) throws GameEngineException {
        for (LevelCondition condition : myLevelConditions) {
            if (condition.hasHappened(this)) {
                condition.getAction().execute(this);
            }
        }
    }

    public double getElapsedTime() {
        return myElapsedTime;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public Integer getNextSpriteInQueue() {
        return mySpritesQueue.remove();
    }

    public Point getSpawnPoint() {
        return mySpawnPoint;
    }

}
