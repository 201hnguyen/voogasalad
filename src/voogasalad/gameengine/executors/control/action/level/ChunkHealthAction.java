package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class ChunkHealthAction implements LevelAction {
    private int mySpriteId;
    private int myDamageValue;
    private boolean isFinished;

    /**
     * Purpose:
     * Assumptions:
     * @param spriteId
     * @param damageValue
     */
    public ChunkHealthAction(int spriteId, int damageValue) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        isFinished = false;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite toAct = level.getSpriteManager().getSpriteById(mySpriteId);
        if(!(toAct == null || toAct.isDead())) {
            toAct.chunkHealth(myDamageValue);
        }
        isFinished = true;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
