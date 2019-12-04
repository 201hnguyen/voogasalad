package voogasalad.gameengine.executors.gamecontrol.action.game;

import voogasalad.gameengine.executors.gamecontrol.Game;

public interface GameAction {
    void execute(Game game);
    boolean isFinished();
}
