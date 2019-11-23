package voogasalad.gameengine.executors.gamecontrol;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.awt.Point;
import java.util.Queue;

public class Wave {
    Queue<Integer> mySpritesQueue;
    double mySpritesInterval;
    Point mySpawnPoint;
    double myElapsedTimeSinceLastEntry;

    public Wave(Queue<Integer> spritesQueue, Double spriteInterval, Point spawnPoint){
        mySpritesInterval = spriteInterval;
        myElapsedTimeSinceLastEntry = mySpritesInterval;
        mySpritesQueue = spritesQueue;
        mySpawnPoint = spawnPoint;
    }

    public boolean spawnNextSprite(SpriteManager spriteManager, double elapsedTime) throws GameEngineException {
        Integer nextSpriteToSpawn = getNextSpriteToSpawn(elapsedTime);
        if (nextSpriteToSpawn != null) {
            spriteManager.makeSpriteFromPrototype(mySpawnPoint.getX(), mySpawnPoint.getY(), nextSpriteToSpawn);
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
