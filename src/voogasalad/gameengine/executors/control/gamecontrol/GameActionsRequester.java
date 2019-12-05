package voogasalad.gameengine.executors.control.gamecontrol;

import voogasalad.gameengine.executors.control.action.game.GameAction;

import java.util.HashSet;
import java.util.Set;

public class GameActionsRequester {
    Set<GameAction> myRequestedActions;

    public GameActionsRequester() {
        myRequestedActions = new HashSet<>();
    }

    public void requestAction(GameAction action) {
        myRequestedActions.add(action);
    }

    public Set<GameAction> getRequestedActions() {
        Set<GameAction> returnSet = myRequestedActions;
        myRequestedActions = new HashSet<>();
        return returnSet;
    }
}
