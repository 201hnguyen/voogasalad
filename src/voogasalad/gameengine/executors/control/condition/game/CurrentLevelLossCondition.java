package voogasalad.gameengine.executors.control.condition.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public class CurrentLevelLossCondition extends GameCondition {

    public CurrentLevelLossCondition(int gameConditionId, ConditionClassification classification, Set<GameAction> actions) {
        super(gameConditionId, classification, actions);
    }

    public CurrentLevelLossCondition(int gameConditionId, Map<String, String> parameters, Set<GameAction> actions) {
        super(gameConditionId, parameters, actions);
    }

    @Override
    public boolean hasHappened(Game game) {
        return game.getCurrentLevelStatus() == Status.LOST;
    }
}
