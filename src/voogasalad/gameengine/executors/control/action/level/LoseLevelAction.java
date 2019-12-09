package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

public class LoseLevelAction implements LevelAction {

    @Override
    public void execute(Level level) {
        level.getStatusManager().setGameSceneStatus(Status.LOST);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
