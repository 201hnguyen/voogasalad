package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

import java.util.Map;
import java.util.Set;

public class LivesCountCondition implements LevelCondition {

    private Set<LevelAction> myActions;
    private int myMarkedCount;
    private ConditionClassification myConditionClassification;

    public LivesCountCondition(int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        myConditionClassification = conditionClassification;
        myMarkedCount = markedCount;
        myActions = actions;
    }

    public LivesCountCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        myMarkedCount = Integer.parseInt(parameters.get("markedcount")); //FIXME: have to do some null checks and not hard code string here
        myConditionClassification = ConditionClassification.valueOf(parameters.get("classification")); // FIXME: have to do some null checks and not hard code string here
        myActions = actions;
    }

    @Override
    public boolean hasHappened(Level level) {
        return level.getStatusManager().getLives()==myMarkedCount;
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
