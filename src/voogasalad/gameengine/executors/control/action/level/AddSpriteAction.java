package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

public class AddSpriteAction implements LevelAction {
    private int myPrototypeIndex;
    private double myXPos;
    private double myYPos;

    public AddSpriteAction(int prototypeIndex, double xpos, double ypos) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
    }

    @Override
    public void execute(Level level) throws GameEngineException {
        level.getSpriteManager().makeSpriteFromPrototype(myXPos, myYPos, myPrototypeIndex);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
