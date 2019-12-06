package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

public class AddSpriteAtAngleAction implements LevelAction {
    private int myPrototypeIndex;
    private double myXPos;
    private double myYPos;
    private double myAngle;

    public AddSpriteAtAngleAction (int prototypeIndex, double xpos, double ypos, double angle) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
        myAngle = angle;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite sprite = level.getSpriteManager().makeSpriteFromPrototype(myXPos, myYPos, myPrototypeIndex);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
