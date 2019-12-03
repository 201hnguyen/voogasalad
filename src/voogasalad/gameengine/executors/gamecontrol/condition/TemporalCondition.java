package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

import java.util.Map;
import java.util.Set;

public class TemporalCondition implements LevelCondition {
    private double myMarkedTime;
    private Set<LevelAction> myActions;
    private ConditionClassification myConditionClassification;

    public TemporalCondition(double markedTime, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        myConditionClassification = conditionClassification;
        myMarkedTime = markedTime;
        myActions = actions;
    }

    public TemporalCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        myConditionClassification = ConditionClassification.valueOf(parameters.get("classification")); //FIXME: have to do some null checks and not hard code string here
        myMarkedTime = Integer.parseInt(parameters.get("markedtime")); //FIXME: have to do some null checks and not hard code string here
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

    @Override
    public ConditionClassification getClassification() {
        return myConditionClassification;
    }
}
