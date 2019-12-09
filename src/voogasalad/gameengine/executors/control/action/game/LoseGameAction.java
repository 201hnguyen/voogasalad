package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Status;

public class LoseGameAction implements GameAction {
    @Override
    public void execute(Game game) {
        game.setGameStatus(Status.LOST);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
