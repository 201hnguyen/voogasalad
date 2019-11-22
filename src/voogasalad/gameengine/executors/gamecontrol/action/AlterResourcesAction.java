package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

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
