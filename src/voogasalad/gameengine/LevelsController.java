package voogasalad.gameengine;

import voogasalad.gameengine.executors.gamecontrol.Level;

import java.util.List;

public class LevelsController {
    private List<Level> myLevels;
    private List<Integer> myLevelsSequence;
    private int myNextLevelIndex;

    public LevelsController(List<Level> levels, List<Integer> levelsSequence) {
        myLevels = levels;
        myLevelsSequence = levelsSequence;
        myNextLevelIndex = 0;
    }

    public Level getNextLevel() {
        for (Level level : myLevels) {
            if (level.getLevelId() == myLevelsSequence.get(myNextLevelIndex)) {
                myNextLevelIndex++;
                return level;
            }
        }
        return null;
    }
}
