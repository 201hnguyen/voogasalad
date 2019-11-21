package voogasalad.gameengine.engine.gamecontrol.action;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;

public class AlterResourcesAction implements LevelAction{

    private int myValue;

    public AlterResourcesAction(int value) {
        myValue = value;
    }
    @Override
    public void execute(Level level) throws GameEngineException {
        level.getStatusManager().alterResourcesByValue(myValue);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
