package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import java.util.Set;

public class TemporalCondition implements LevelCondition {
    private double myMarkedTime;
    private Set<LevelAction> myActions;

    public TemporalCondition(double markedTime, Set<LevelAction> actions) {
        myMarkedTime = markedTime;
        myActions = actions;
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getTotalElapsedTime() >= myMarkedTime;
    }

    @Override
    public Set<LevelAction> getActions() {
        return myActions;
    }
}
