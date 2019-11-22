package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

public interface LevelAction {
    void execute(Level level) throws GameEngineException;
    boolean isFinished();
}
