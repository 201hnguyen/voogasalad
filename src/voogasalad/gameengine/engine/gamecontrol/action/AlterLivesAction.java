package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;

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
