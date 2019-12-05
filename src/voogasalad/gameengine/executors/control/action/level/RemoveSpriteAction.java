package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

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
