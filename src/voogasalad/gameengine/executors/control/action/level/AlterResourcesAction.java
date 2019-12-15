package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

public class AlterResourcesAction implements LevelAction{

    private int myValue;

    /**
     * Purpose:
     * Assumptions:
     * @param value
     */
    public AlterResourcesAction(int value) {
        myValue = value;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        level.getStatusManager().alterResourcesByValue(myValue);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
