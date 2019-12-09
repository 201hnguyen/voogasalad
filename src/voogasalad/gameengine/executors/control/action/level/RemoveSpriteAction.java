package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;

public class RemoveSpriteAction implements LevelAction{
    private int mySpriteId;

    public RemoveSpriteAction(int spriteId) {
        mySpriteId = spriteId;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite removed = level.getSpriteManager().removeSpriteById(mySpriteId);
        level.getStatusManager().alterResourcesByValue(removed.getDestroyCost());
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
