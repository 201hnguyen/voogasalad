package voogasalad.gameengine.executors.gamecontrol.action.game;

import voogasalad.gameengine.Engine;

public class AdvanceToNextLevelAction implements GameAction{
    @Override
    public void execute(Engine engine) {
        engine.loadNextLevel();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
