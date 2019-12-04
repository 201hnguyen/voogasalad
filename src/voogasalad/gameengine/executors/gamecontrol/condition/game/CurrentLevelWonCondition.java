package voogasalad.gameengine.executors.gamecontrol.condition.game;

import voogasalad.gameengine.Engine;
import voogasalad.gameengine.executors.gamecontrol.GameSceneStatus;
import voogasalad.gameengine.executors.gamecontrol.action.level.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public class CurrentLevelWonCondition extends GameCondition {
    public CurrentLevelWonCondition(ConditionClassification classification, Set<LevelAction> actions) {
        super(classification, actions);
    }

    public CurrentLevelWonCondition(Map<String, String> parameters, Set<LevelAction> actions) {
        super(parameters, actions);
    }

    @Override
    public boolean hasHappened(Engine gameEngine) {
        return gameEngine.getCurrentLevelStatus() == GameSceneStatus.WON;
    }
}
