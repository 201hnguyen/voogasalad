package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

public class RemoveSpriteTowerAction implements LevelAction {

    private double myXPos;
    private double myYPos;

    public RemoveSpriteTowerAction(double xpos, double yPos) {
        myXPos = xpos;
        myYPos = yPos;
    }


    @Override
    public void execute(Level level) {
        level.getSpriteManager().removeSpriteTowerByCoordinates(myXPos, myYPos);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
