package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface GameAction {
    void execute(Game game) throws GameEngineException;
    boolean isFinished();
}
