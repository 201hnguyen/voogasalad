package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

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
