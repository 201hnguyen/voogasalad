package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;

public interface GameAction {
    void execute(Game game);
    boolean isFinished();
}
