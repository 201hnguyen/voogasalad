package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

public class WinGameAction implements GameAction {
    @Override
    public void execute(Game game) {
        game.setGameStatus(Status.WON);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
