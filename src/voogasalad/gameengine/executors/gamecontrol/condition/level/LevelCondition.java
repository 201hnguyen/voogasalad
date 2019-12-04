package voogasalad.gameengine.executors.gamecontrol.condition.level;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.level.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.Condition;
import voogasalad.gameengine.executors.gamecontrol.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public abstract class LevelCondition extends Condition {
    public LevelCondition(ConditionClassification classification, Set<LevelAction> actions) {
        super(classification, actions);
    }

    public LevelCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters,actions);
    }

    public abstract boolean hasHappened(Level level);
}
