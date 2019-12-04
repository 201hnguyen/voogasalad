package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public abstract class LevelCondition {
    public static final String CONDITION_CLASSIFICATION_MAP_KEY = "classification";
    public static final ConditionClassification DEFAULT_CONDITION_CLASSIFICATION = ConditionClassification.ONETIME;

    private Set<LevelAction> myActions;
    private ConditionClassification myConditionClassification;

    public LevelCondition(ConditionClassification classification, Set<LevelAction> actions) {
        myActions = actions;
        myConditionClassification = classification;
    }

    public LevelCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        try {
            myConditionClassification = ConditionClassification.valueOf(parameters.get(CONDITION_CLASSIFICATION_MAP_KEY));
        } catch (NullPointerException | IllegalArgumentException e) {
            myConditionClassification = DEFAULT_CONDITION_CLASSIFICATION;
        }
        myActions = actions;
    }

    public ConditionClassification getClassification() {
        return myConditionClassification;
    }

    public Set<LevelAction> getLevelActions() {
        return myActions;
    }

    public abstract boolean hasHappened(Level level);
}
