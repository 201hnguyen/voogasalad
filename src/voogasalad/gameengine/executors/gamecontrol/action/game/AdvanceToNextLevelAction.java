package voogasalad.gameengine.executors.gamecontrol.action.game;

import voogasalad.gameengine.executors.gamecontrol.Game;

public class AdvanceToNextLevelAction implements GameAction{
    @Override
    public void execute(Game game) {
        game.loadNextLevel();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
