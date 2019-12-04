package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public class LivesCountCondition extends LevelCondition {

    public static final int DEFAULT_MARKED_COUNT = 0;
    public static final String MARKED_COUNT_MAP_KEY = "markedcount";

    private int myMarkedCount;

    public LivesCountCondition(int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(conditionClassification, actions);
        myMarkedCount = markedCount;
    }

    public LivesCountCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
        try {
            myMarkedCount = Integer.parseInt(parameters.get(MARKED_COUNT_MAP_KEY));
        } catch (NullPointerException | NumberFormatException e) {
            myMarkedCount = DEFAULT_MARKED_COUNT;
        }
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getLives()==myMarkedCount;
    }
}
