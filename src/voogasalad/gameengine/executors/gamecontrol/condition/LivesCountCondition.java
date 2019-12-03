package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

import java.util.Map;
import java.util.Set;

public class LivesCountCondition extends LevelCondition {

    private int myMarkedCount;

    public LivesCountCondition(int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(conditionClassification, actions);
        myMarkedCount = markedCount;
    }

    public LivesCountCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
        myMarkedCount = Integer.parseInt(parameters.get("markedcount")); //FIXME: have to do some null checks and not hard code string here
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getLives()==myMarkedCount;
    }
}
