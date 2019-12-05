package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.control.gamecontrol.GameActionsRequester;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.action.level.RemoveSpriteAction;

public class UIActionsProcessor {
    private LevelActionsRequester myLevelActionsRequester;
    private GameActionsRequester myGameActionsRequester;

    public UIActionsProcessor(LevelActionsRequester levelActionsRequester, GameActionsRequester gameActionsRequester) {
        myGameActionsRequester = gameActionsRequester;
        myLevelActionsRequester = levelActionsRequester;
    }

    public void processAddSpriteAction(int prototypeId, double xPos, double yPos) {
        LevelAction action = new AddSpriteAction(prototypeId, xPos, yPos);
        myLevelActionsRequester.requestAction(action);
    }

    public void processRemoveSpriteAction(int spriteId) {
        LevelAction action = new RemoveSpriteAction(spriteId);
        myLevelActionsRequester.requestAction(action);
    }

    public void updateLevel(Level level) {
        myLevelActionsRequester = level.getActionsRequester();
    }
}
