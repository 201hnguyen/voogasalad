package voogasalad.gameengine.executors.control.levelcontrol.managers;

import voogasalad.gameengine.executors.control.levelcontrol.GameSceneStatus;

public class LevelStatusManager {

    public static final GameSceneStatus INITIAL_GAME_SCENE_STATUS = GameSceneStatus.INACTIVE;

    private double myTotalElapsedTime;
    private double myElapsedTimeSinceLastFrame;
    private int myResources;
    private int myLives;
    private GameSceneStatus myGameSceneStatus;
    private int myScore;

    public LevelStatusManager() {
        myResources = 0;
        myLives = 0;
        myTotalElapsedTime = 0;
        myElapsedTimeSinceLastFrame = 0;
        myScore = 0;
        myGameSceneStatus = INITIAL_GAME_SCENE_STATUS;
    }

    public LevelStatusManager (int resources, int lives, int score, double totalElapsedTime, double elapsedTimeSinceLastFrame, GameSceneStatus gameSceneStatus) {
        myTotalElapsedTime = totalElapsedTime;
        myElapsedTimeSinceLastFrame = elapsedTimeSinceLastFrame;
        myResources = resources;
        myScore = score;
        myLives = lives;
        myGameSceneStatus = gameSceneStatus;
    }

    public void setGameSceneStatus(GameSceneStatus status) {
        myGameSceneStatus = status;
    }

    public GameSceneStatus getGameSceneStatus() {
        return myGameSceneStatus;
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

    public int alterScoreByValue(int value) {
        myScore += value;
        return myScore;
    }

    public int getScore() {
        return myScore;
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

    public LevelStatusManager getCopyOfStatusManager() {
        return new LevelStatusManager(myResources, myLives, myScore, myTotalElapsedTime, myElapsedTimeSinceLastFrame, myGameSceneStatus);
    }
}
