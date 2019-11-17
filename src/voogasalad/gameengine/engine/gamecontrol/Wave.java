package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.awt.Point;
import java.util.Queue;

public class Wave {
    Queue<Integer> mySpritesQueue;
    double mySpritesInterval;
    Point mySpawnPoint;
    double myElapsedTimeSinceLastEntry;

    public Wave(Queue<Integer> spritesQueue, Double spriteInterval, Point spawnPoint) throws GameEngineException {
        mySpritesInterval = spriteInterval;
        myElapsedTimeSinceLastEntry = mySpritesInterval;
        mySpritesQueue = spritesQueue;
        mySpawnPoint = spawnPoint;
    }

    public Integer getNextSpriteToSpawn(double elapsedTime){
        myElapsedTimeSinceLastEntry +=elapsedTime;
        System.out.println("Wave current time: " + myElapsedTimeSinceLastEntry);
        if (myElapsedTimeSinceLastEntry >= mySpritesInterval) {
            myElapsedTimeSinceLastEntry = 0;
            return mySpritesQueue.remove();
        }
        return null;
    }

    public Point getSpawnPoint() {
        return mySpawnPoint;
    }

    public boolean isEmpty() {
        return mySpritesQueue.size() == 0;
    }
}
