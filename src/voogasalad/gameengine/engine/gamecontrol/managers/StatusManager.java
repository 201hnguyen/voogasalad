package voogasalad.gameengine.engine.gamecontrol.managers;

public class StatusManager {

    private double myTotalElapsedTime;
    private double myElapsedTimeSinceLastFrame;
    private int myResources;
    private int myLives;

    public StatusManager(int resources, int lives) {
        myTotalElapsedTime = 0;
        myElapsedTimeSinceLastFrame = 0;
        myResources = resources;
        myLives = lives;
    }

    public void notifyNewCycle(double elapsedTime) {
        myElapsedTimeSinceLastFrame = elapsedTime;
        myTotalElapsedTime += elapsedTime;
    }

    public double getTotalElapsedTime() {
        return myTotalElapsedTime;
    }

    public double getElapsedTimeSinceLastFrame() {
        return myElapsedTimeSinceLastFrame;
    }

    public int getResources() {
        return myResources;
    }

    public int getLives() {
        return myLives;
    }

    public int alterResourcesByValue(int value) {
        myResources += value;
        return myResources;
    }

    public int alterLivesByValue(int value) {
        myLives += value;
        return myLives;
    }
}
