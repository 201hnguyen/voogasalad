package voogasalad.gameengine.executors.control.gamecontrol.controllers;

import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameRulesController {

    private Collection<GameCondition> myGameConditions;
    private Collection<GameAction> myGameActionsToExecute;

    public GameRulesController() {
        myGameActionsToExecute = new HashSet<>();
        myGameConditions = new HashSet<>();
    }

    public void addGameConditionsAsCollection(Collection<GameCondition> gameConditions) {
        myGameConditions.addAll(gameConditions);
    }


    public void checkConditionsAndRunGameActions(Game game) throws GameEngineException {
        Set<GameCondition> conditionsToRemove = new HashSet<>();
        for (GameCondition condition : myGameConditions) {
            if (condition.hasHappened(game)) {
                myGameActionsToExecute.addAll(condition.getGameActions());
                if (condition.getClassification()== ConditionClassification.ONETIME) {
                    conditionsToRemove.add(condition);
                }
            }
        }
        conditionsToRemove.stream().
                forEach(condition -> myGameConditions.remove(condition));
        executeGameActions(game);
    }

    private void executeGameActions(Game game) throws GameEngineException {
        Set<GameAction> actionsToRemove = new HashSet<>();
        for (GameAction action : myGameActionsToExecute) {
            action.execute(game);
            if (action.isFinished()) {
                actionsToRemove.add(action);
            }
        }
        actionsToRemove.stream().
                forEach(action -> myGameActionsToExecute.remove(action));
    }

    public Collection<GameCondition> getGameConditions() {
        return myGameConditions;
    }

    public void addGameActions(Collection<GameAction> actions) {
        myGameActionsToExecute.addAll(actions);
    }
}
