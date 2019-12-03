package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

public class LoseLevelAction implements LevelAction {
    @Override
    public void execute(Level level) throws GameEngineException {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
