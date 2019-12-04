package voogasalad.gameengine.executors.gamecontrol.action.game;

import voogasalad.gameengine.Engine;

public interface GameAction {
    void execute(Engine engine);
    boolean isFinished();
}
