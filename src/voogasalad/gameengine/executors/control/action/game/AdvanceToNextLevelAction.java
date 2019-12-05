package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;

public class AdvanceToNextLevelAction implements GameAction{
    @Override
    public void execute(Game game) {
        game.loadNextLevel();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
