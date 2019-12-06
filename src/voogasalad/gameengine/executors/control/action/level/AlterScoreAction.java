package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;

public class AlterScoreAction implements LevelAction{

    private int myValue;

    public AlterScoreAction(int value) {
        myValue = value;
    }

    @Override
    public void execute(Level level) {
        level.getStatusManager().alterScoreByValue(myValue);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
