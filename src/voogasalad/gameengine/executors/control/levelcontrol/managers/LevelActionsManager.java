package voogasalad.gameengine.executors.control.levelcontrol.managers;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LevelActionsManager {
    private Set<LevelAction> myActionsInProgress;

    public LevelActionsManager() {
        myActionsInProgress = new HashSet<>();
    }

    public void executeLevelActions(Level level) throws GameEngineException {
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

    public void addLevelActionsAsCollection(Collection<LevelAction> levelActions) {
        myActionsInProgress.addAll(levelActions);
    }
    
    public void addAction(LevelAction action) {
        myActionsInProgress.add(action);
    }
        
}

