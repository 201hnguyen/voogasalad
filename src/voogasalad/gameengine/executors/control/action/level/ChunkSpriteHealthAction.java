package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

public class ChunkSpriteHealthAction implements LevelAction {
    private int mySpriteId;
    private int myDamageValue;
    private boolean isFinished;

    public ChunkSpriteHealthAction(int spriteId, int damageValue) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        isFinished = false;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite toAct = level.getSpriteManager().getSpriteById(mySpriteId);
        toAct.chunkHealth(myDamageValue);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
