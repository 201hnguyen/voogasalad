package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class FreezeSpriteAction implements LevelAction {

    private int mySpriteId;
    private int myDamageValue;
    private double myDuration;
    private boolean isFinished;

    /**
     * Purpose:
     * Assumptions:
     * @param spriteId
     * @param damageValue
     * @param duration
     */
    public FreezeSpriteAction(int spriteId, int damageValue, double duration) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        myDuration = duration;
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
            if(!toAct.isDead()) {
                toAct.delayMovement(myDuration);
            }
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
