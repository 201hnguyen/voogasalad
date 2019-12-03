package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

import java.util.Map;
import java.util.Set;

public abstract class LevelCondition {

    private Set<LevelAction> myActions;
    private ConditionClassification myConditionClassification;

    public LevelCondition(ConditionClassification classification, Set<LevelAction> actions) {
        myActions = actions;
        myConditionClassification = classification;
    }

    public LevelCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        myConditionClassification = ConditionClassification.valueOf(parameters.get("classification"));
        myActions = actions;
    }

    public abstract boolean hasHappened(Level level);

    public Set<LevelAction> getActions() {
        return myActions;
    }

    public ConditionClassification getClassification() {
        return myConditionClassification;
    }
}
