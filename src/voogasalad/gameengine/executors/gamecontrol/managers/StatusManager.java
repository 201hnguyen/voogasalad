package voogasalad.gameengine.executors.gamecontrol.managers;

public class StatusManager {

    private double myTotalElapsedTime;
    private double myElapsedTimeSinceLastFrame;
    private int myResources;
    private int myLives;

    public StatusManager() {
        myResources = 0;
        myLives = 0;
        myTotalElapsedTime = 0;
        myElapsedTimeSinceLastFrame = 0;
    }

    public void setResources(int value) {
        myResources = value;
    }

    public void setLives(int value) {
        myLives = value;
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
