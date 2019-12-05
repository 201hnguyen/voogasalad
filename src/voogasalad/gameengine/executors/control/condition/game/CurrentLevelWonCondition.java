package voogasalad.gameengine.executors.control.condition.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.GameSceneStatus;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public class CurrentLevelWonCondition extends GameCondition {
    public CurrentLevelWonCondition(ConditionClassification classification, Set<GameAction> actions) {
        super(classification, actions);
    }

    public CurrentLevelWonCondition(Map<String, String> parameters, Set<GameAction> actions) {
        super(parameters, actions);
    }

    @Override
    public boolean hasHappened(Game game) {
        return game.getCurrentLevelStatus() == GameSceneStatus.WON;
    }
}
