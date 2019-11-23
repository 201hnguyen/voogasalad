package voogasalad.gameengine.executors.gamecontrol.managers;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

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

    public void addActionsAsCollection(Set<LevelAction> levelActions) {
        myActionsInProgress.addAll(levelActions);
    }
    
    public void addAction(LevelAction action) {
        myActionsInProgress.add(action);
    }
        
}

