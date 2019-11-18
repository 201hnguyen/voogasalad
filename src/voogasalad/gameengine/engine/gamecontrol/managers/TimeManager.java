package voogasalad.gameengine.engine.gamecontrol.managers;

public class TimeManager {

    private double myTotalElapsedTime;
    private double myElapsedTimeSinceLastFrame;

    public TimeManager() {
        myTotalElapsedTime = 0;
        myElapsedTimeSinceLastFrame = 0;
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
}
