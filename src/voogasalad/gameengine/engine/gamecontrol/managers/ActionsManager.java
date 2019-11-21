package voogasalad.gameengine.engine.gamecontrol.managers;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;

import java.util.HashSet;
import java.util.Set;

public class ActionsManager {
    private Set<LevelAction> myActionsInProgress;

    public ActionsManager() {
        myActionsInProgress = new HashSet<>();
    }

    public void executeActions(Level level) throws GameEngineException {
        Set<LevelAction> actionsToRemove = new HashSet<>();
        for (LevelAction action : myActionsInProgress) {
            action.execute(level);
            if (action.isFinished()) {
                actionsToRemove.add(action);
            }
        }
        actionsToRemove.stream().
                forEach(action -> myActionsInProgress.remove(action));
    }

    public void addNewActions(Set<LevelAction> levelActions) {
        myActionsInProgress.addAll(levelActions);
    }
}

