package voogasalad.gameengine.executors.gamecontrol.action.level;

import voogasalad.gameengine.executors.gamecontrol.GameSceneStatus;
import voogasalad.gameengine.executors.gamecontrol.Level;

public class LoseLevelAction implements LevelAction {

    @Override
    public void execute(Level level) {
        level.getStatusManager().setGameSceneStatus(GameSceneStatus.LOST);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
