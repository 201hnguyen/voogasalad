package voogasalad.gameengine.executors.control.condition.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;

public abstract class GameCondition {
    public static final String CONDITION_CLASSIFICATION_MAP_KEY = "Classification";
    public static final ConditionClassification DEFAULT_CONDITION_CLASSIFICATION = ConditionClassification.CONTINUOUS;

    private Set<GameAction> myActions;
    private ConditionClassification myConditionClassification;

    public GameCondition(ConditionClassification classification, Set<GameAction> actions) {
        myActions = actions;
        myConditionClassification = classification;
    }

    public GameCondition(Map<String, String> parameters, Set<GameAction> actions) {
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

    public Set<GameAction> getGameActions() {
        return myActions;
    }

    public abstract boolean hasHappened(Game game);

}
