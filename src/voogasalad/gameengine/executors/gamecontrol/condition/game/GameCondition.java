package voogasalad.gameengine.executors.gamecontrol.condition.game;

import voogasalad.gameengine.Engine;
import voogasalad.gameengine.executors.gamecontrol.action.level.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.Condition;
import voogasalad.gameengine.executors.gamecontrol.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public abstract class GameCondition extends Condition {
    public GameCondition(ConditionClassification classification, Set<LevelAction> actions) {
        super(classification, actions);
    }

    public GameCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
    }

    public abstract boolean hasHappened(Engine gameEngine);

}
