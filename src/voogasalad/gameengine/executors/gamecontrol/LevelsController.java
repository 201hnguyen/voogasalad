package voogasalad.gameengine.executors.gamecontrol;

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

    private boolean hasNextLevel() {
        if (myLevelsSequence == null) {
            return false;
        } else {
            return myNextLevelIndex < myLevelsSequence.size();
        }
    }
}
