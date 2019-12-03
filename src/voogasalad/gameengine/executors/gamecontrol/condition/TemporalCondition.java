package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

import java.util.Map;
import java.util.Set;

public class TemporalCondition extends LevelCondition {
    private double myMarkedTime;
    private Set<LevelAction> myActions;
    private ConditionClassification myConditionClassification;

    public TemporalCondition(double markedTime, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(conditionClassification, actions);
        myMarkedTime = markedTime;
    }

    public TemporalCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
        myMarkedTime = Integer.parseInt(parameters.get("markedtime")); //FIXME: have to do some null checks and not hard code string here
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getTotalElapsedTime() >= myMarkedTime;
    }
}
