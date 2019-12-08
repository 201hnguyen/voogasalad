package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;

public class SellTowerAction implements LevelAction {

    private double myXPos;
    private double myYPos;

    public SellTowerAction(double xpos, double yPos) {
        myXPos = xpos;
        myYPos = yPos;
    }


    @Override
    public void execute(Level level) {
        Sprite removed = level.getSpriteManager().removeSpriteTowerByCoordinates(myXPos, myYPos);
        level.getStatusManager().alterResourcesByValue(removed.getDestroyCost());
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
