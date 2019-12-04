package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.gamecontrol.action.AddSpriteAction;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.action.RemoveSpriteAction;

public class UIActionsProcessor {
    private LevelActionsRequester myLevelActionsRequester;

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
