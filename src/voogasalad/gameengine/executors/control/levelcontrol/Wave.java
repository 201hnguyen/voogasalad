package voogasalad.gameengine.executors.control.levelcontrol;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Queue;

public class Wave {
    Queue<Integer> mySpritesQueue;
    double mySpritesInterval;
    Point mySpawnPoint;
    double myElapsedTimeSinceLastEntry;
    List<Point2D.Double> myPath;

    public Wave(Queue<Integer> spritesQueue, Double spriteInterval, Point spawnPoint, List<Point2D.Double> path){
        mySpritesInterval = spriteInterval;
        myElapsedTimeSinceLastEntry = mySpritesInterval;
        mySpritesQueue = spritesQueue;
        mySpawnPoint = spawnPoint;
        myPath = path;
    }

    public boolean spawnNextSprite(SpriteManager spriteManager, double elapsedTime) throws GameEngineException {
        Integer nextSpriteToSpawn = getNextSpriteToSpawn(elapsedTime);
        if (nextSpriteToSpawn != null) {
            Sprite spawnedSprite = spriteManager.makeSpriteFromPrototype(mySpawnPoint.getX(), mySpawnPoint.getY(), nextSpriteToSpawn);
            spawnedSprite.updatePath(myPath);
        }
        return isEmpty();
    }

    private Integer getNextSpriteToSpawn(double elapsedTime){
        myElapsedTimeSinceLastEntry +=elapsedTime;
        if (myElapsedTimeSinceLastEntry >= mySpritesInterval) {
            myElapsedTimeSinceLastEntry = 0;
            return mySpritesQueue.remove();
        }
        return null;
    }

    private boolean isEmpty() {
        return mySpritesQueue.size() == 0;
    }
}
