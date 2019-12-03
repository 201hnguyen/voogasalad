package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.gamecontrol.GameSceneStatus;
import voogasalad.gameengine.executors.gamecontrol.Level;

public class WinLevelAction implements LevelAction {

    @Override
    public void execute(Level level) {
        level.getStatusManager().setGameSceneStatus(GameSceneStatus.WON);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
