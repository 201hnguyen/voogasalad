package voogasalad.gameengine.executors.control.action.game;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.*;
/**
 * Class: GameSceneObject
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class EditGameConditionsAction implements GameAction{

    private NodeList myEditedConditionsList;

    /**
     * Purpose:
     * Assumptions:
     * @param editableObjectRoot
     */
    public EditGameConditionsAction(Element editableObjectRoot) {
        myEditedConditionsList = editableObjectRoot.getElementsByTagName("GameCondition");
    }

    /**
     * Purpose:
     * Assumptions:
     * @param game
     * @throws GameEngineException
     */
    @Override
    public void execute(Game game) throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        Collection<GameCondition> editedGameConditions = conditionsConfigurator.buildGameConditionsCollection(myEditedConditionsList);
        Collection<GameCondition> currentGameConditions = game.getAllGameConditions();
        Set<GameCondition> originalConditionsToRemove = new HashSet<>();
        for (GameCondition newCondition : editedGameConditions) {
            for (GameCondition oldCondition : currentGameConditions) {
                if (newCondition.getGameConditionId() == oldCondition.getGameConditionId()) {
                    originalConditionsToRemove.add(oldCondition);
                }
            }
        }
        currentGameConditions.removeAll(originalConditionsToRemove);
        currentGameConditions.addAll(editedGameConditions);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
