package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;

public class AddSpriteAction implements LevelAction {
    private int myPrototypeIndex;
    private double myXPos;
    private double myYPos;
    private Double myAngle;

    public AddSpriteAction(int prototypeIndex, double xpos, double ypos) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
    }

    public AddSpriteAction(int prototypeIndex, double xpos, double ypos, double angle) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
        myAngle = angle;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite sprite = level.getSpriteManager().makeSpriteFromPrototype(myXPos, myYPos, myPrototypeIndex);
        int scoreToSubtract = sprite.getCreateCost();
        level.getStatusManager().alterResourcesByValue(-scoreToSubtract);
        if (myAngle != null) {
            sprite.updateMovementAngle(myAngle);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
