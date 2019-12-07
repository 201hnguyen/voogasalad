package voogasalad.gameengine.executors.control.gamecontrol.controllers;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;

import java.util.List;

public class GameLevelsController {
    private List<Level> myLevels;
    private List<Integer> myLevelsSequence;
    private int myNextLevelIndex;

    public GameLevelsController(List<Level> levels, List<Integer> levelsSequence) {
        myLevels = levels;
        myLevelsSequence = levelsSequence;
        myNextLevelIndex = 0;
    }

    public Level getNextLevel(Level currentLevel) {
        if (hasNextLevel()) {
            for (Level level : myLevels) {
                if (level.getLevelId() == myLevelsSequence.get(myNextLevelIndex)) {
                    myNextLevelIndex++;
                    return level;
                }
            }
        }
        return currentLevel;
    }

    public Level loadBaseLevel() throws GameEngineException {
        return new LevelBuilder(-1).build();

    }

    private boolean hasNextLevel() {
        if (myLevelsSequence == null) {
            return false;
        } else {
            return myNextLevelIndex < myLevelsSequence.size();
        }
    }

    public int getTotalScoreForAllLevels() {
        int totalScore = 0;
        for (Level level : myLevels) {
            totalScore+=level.getCurrentScore();
        }
        return totalScore;
    }

    public List<Level> getLevels() {
        return myLevels;
    }
}
