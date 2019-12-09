package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

public class WinLevelAction implements LevelAction {

    @Override
    public void execute(Level level) {
        level.getStatusManager().setGameSceneStatus(Status.WON);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
