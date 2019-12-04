package voogasalad.gameengine.executors.gamecontrol.condition.level;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.level.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public class TemporalCondition extends LevelCondition {

    public static final String MARKED_TIME_MAP_KEY = "markedtime";
    public static final int DEFAULT_MARKED_TIME = 0;

    private double myMarkedTime;

    public TemporalCondition(double markedTime, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(conditionClassification, actions);
        myMarkedTime = markedTime;
    }

    public TemporalCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
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
