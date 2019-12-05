package voogasalad.gameengine.executors.control.levelcontrol;

import voogasalad.gameengine.executors.control.action.level.LevelAction;

import java.util.HashSet;
import java.util.Set;

public class LevelActionsRequester {
    Set<LevelAction> myRequestedActions;

    public LevelActionsRequester() {
        myRequestedActions = new HashSet<>();
    }

    public void requestAction(LevelAction action) {
        myRequestedActions.add(action);
    }

    public Set<LevelAction> getRequestedActions() {
        Set<LevelAction> returnSet = myRequestedActions;
        myRequestedActions = new HashSet<>();
        return returnSet;
    }
}
