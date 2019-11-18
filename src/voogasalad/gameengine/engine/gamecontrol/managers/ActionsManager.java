package voogasalad.gameengine.engine.gamecontrol.managers;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;

import java.util.HashSet;
import java.util.Set;

public class ActionsManager {
    private Set<LevelAction> myActionsInProgress;
    private Set<LevelAction> myActionsToRemove;

    public ActionsManager() {
        myActionsInProgress = new HashSet<>();
        myActionsToRemove = new HashSet<>();
    }

    public void executeActions(Level level) throws GameEngineException {
        for (LevelAction action : myActionsInProgress) {
            action.execute(level);
            if (action.isFinished()) {
                myActionsToRemove.add(action);
            }
        }
        removeFinishedActions();
    }

    private void removeFinishedActions() {
        for (LevelAction action : myActionsToRemove) {
            myActionsInProgress.remove(action);
        }
        myActionsToRemove.clear();
    }

    public void addNewActions(Set<LevelAction> levelActions) {
        myActionsInProgress.addAll(levelActions);
    }
}

