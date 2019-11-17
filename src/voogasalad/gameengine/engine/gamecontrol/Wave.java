package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.awt.Point;
import java.util.Queue;

public class Wave {
    Queue<Integer> mySpritesQueue;
    Queue<Integer> mySpritesEntryTimeQueue;
    Point mySpawnPoint;
    double myTotalElapsedTime;
    double myNextEntryTime;

    public Wave(Queue<Integer> spritesQueue, Queue<Integer> spritesEntryTimeQueue, Point spawnPoint) throws GameEngineException {
        if (spritesAndEntryQueueSizesMismatch(spritesQueue, spritesEntryTimeQueue)) {
            throw new GameEngineException("SpritesAndEntryQueueSizesMismatch");
        }
        myTotalElapsedTime = 0;
        mySpritesQueue = spritesQueue;
        mySpritesEntryTimeQueue = spritesEntryTimeQueue;
        myNextEntryTime = 0;
        mySpawnPoint = spawnPoint;
    }

    public Integer getNextSpriteToSpawn(double elapsedTime){
        myTotalElapsedTime +=elapsedTime;
        if (myTotalElapsedTime >= myNextEntryTime) {
            return mySpritesQueue.remove();
        }
        return null;
    }

    public void setNextEntryTime() throws GameEngineException {
        if (mySpritesEntryTimeQueue.size()!=0 && myNextEntryTime<=myTotalElapsedTime) {
            myNextEntryTime = mySpritesEntryTimeQueue.remove();
            if (myNextEntryTime <= myTotalElapsedTime) {
                throw new GameEngineException("InvalidTimeSpecifiedForSpriteEntry");
            }
        }
    }

    public Point getSpawnPoint() {
        return mySpawnPoint;
    }

    public boolean isEmpty() {
        return mySpritesQueue.size() == 0;
    }

    private boolean spritesAndEntryQueueSizesMismatch(Queue<Integer> spritesQueue, Queue<Integer> spritesEntryTimeQueue) {
        return spritesQueue.size() != spritesEntryTimeQueue.size()+1;
    }
}
