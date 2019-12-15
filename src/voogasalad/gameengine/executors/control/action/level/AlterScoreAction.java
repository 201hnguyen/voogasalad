package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class AlterScoreAction implements LevelAction{

    private int myValue;

    /**
     * Purpose:
     * Assumptions:
     * @param value
     */
    public AlterScoreAction(int value) {
        myValue = value;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     */
    @Override
    public void execute(Level level) {
        level.getStatusManager().alterScoreByValue(myValue);
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
