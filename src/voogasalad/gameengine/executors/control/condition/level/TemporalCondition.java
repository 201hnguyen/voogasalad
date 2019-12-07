package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public class TemporalCondition extends LevelCondition {

    public static final String MARKED_TIME_MAP_KEY = "MarkedTime";
    public static final int DEFAULT_MARKED_TIME = 0;

    private double myMarkedTime;

    public TemporalCondition(int levelConditionId, double markedTime, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(levelConditionId, conditionClassification, actions);
        myMarkedTime = markedTime;
    }

    public TemporalCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        super(levelConditionId, parameters, actions);
        try {
            myMarkedTime = Integer.parseInt(parameters.get(MARKED_TIME_MAP_KEY));
        } catch (NullPointerException | NumberFormatException e) {
            myMarkedTime = DEFAULT_MARKED_TIME;
        }
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getTotalElapsedTime() >= myMarkedTime;
    }
}
