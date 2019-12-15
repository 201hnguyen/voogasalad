package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.List;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class DoNothingActionForTest implements GameAction{

    /**
     * Purpose:
     * Assumptions:
     * @param game
     */
    @Override
    public void execute(Game game) throws GameEngineException {
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
