package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class AddSpriteAction implements LevelAction {
    private int myPrototypeIndex;
    private double myXPos;
    private double myYPos;
    private Double myAngle;

    /**
     * Purpose:
     * Assumptions:
     * @param prototypeIndex
     * @param xpos
     * @param ypos
     */
    public AddSpriteAction(int prototypeIndex, double xpos, double ypos) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param prototypeIndex
     * @param xpos
     * @param ypos
     * @param angle
     */
    public AddSpriteAction(int prototypeIndex, double xpos, double ypos, double angle) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
        myAngle = angle;
    }

    /**
     * Purpose:
     * Assumptons:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite spritePrototype = level.getSpriteManager().getCopyPrototype(myPrototypeIndex);
        if (spritePrototype.getCreateCost() <= level.getStatusManager().getResources()) {
            Sprite sprite = level.getSpriteManager().makeSpriteFromPrototype(myXPos, myYPos, myPrototypeIndex);
            int scoreToSubtract = sprite.getCreateCost();
            level.getStatusManager().alterResourcesByValue(-scoreToSubtract);
            if (myAngle != null) {
                sprite.updateMovementAngle(myAngle);
            }
        }
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
