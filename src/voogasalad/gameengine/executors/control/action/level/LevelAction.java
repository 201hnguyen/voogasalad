package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

public interface LevelAction {
    void execute(Level level) throws GameEngineException;
    boolean isFinished();
}
