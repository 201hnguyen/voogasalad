package voogasalad.gameengine.executors.gamecontrol.action;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;

public class RemoveSpriteAction implements LevelAction{
    private int mySpriteId;

    public RemoveSpriteAction(int spriteId) {
        mySpriteId = spriteId;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        level.getSpriteManager().removeSpriteById(mySpriteId);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
