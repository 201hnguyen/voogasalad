package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public interface GameAction {
    /**
     * Purpose:
     * Assumptions:
     * @param game
     * @throws GameEngineException
     */
    void execute(Game game) throws GameEngineException;

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    boolean isFinished();
}
