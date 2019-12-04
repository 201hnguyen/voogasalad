package voogasalad.gameengine.executors.gamecontrol;

import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

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
        return myRequestedActions;
    }
}
