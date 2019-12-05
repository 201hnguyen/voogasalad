package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

public class AlterLivesAction implements LevelAction {
    private int myValue;

    public AlterLivesAction(int value) {
        myValue = value;
    }
    @Override
    public void execute(Level level) throws GameEngineException {
        level.getStatusManager().alterLivesByValue(myValue);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
