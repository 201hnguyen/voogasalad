package voogasalad.gameengine.engine.gamecontrol;

import java.awt.Point;
import java.util.Queue;

public class Wave {
    Queue<Integer> mySpritesQueue;
    Point mySpawnPoint;

    public Wave(Queue<Integer> spritesQueue, Point spawnPoint) {
        mySpritesQueue = spritesQueue;
        mySpawnPoint = spawnPoint;
    }

    public Integer getNextSpriteToSpawn() {
        return mySpritesQueue.remove();
    }

    public Point getSpawnPoint() {
        return mySpawnPoint;
    }

    public boolean isEmpty() {
        return mySpritesQueue.size() == 0;
    }
}
