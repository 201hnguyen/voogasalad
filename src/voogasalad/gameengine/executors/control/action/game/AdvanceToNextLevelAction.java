package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class AdvanceToNextLevelAction implements GameAction{
    /**
     * Purpose:
     * Assumptions:
     * @param game
     */
    @Override
    public void execute(Game game) {
        game.loadNextLevel();
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
