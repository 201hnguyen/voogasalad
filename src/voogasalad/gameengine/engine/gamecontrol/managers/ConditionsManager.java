package voogasalad.gameengine.engine.gamecontrol.managers;

import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConditionsManager {
    private Set<LevelCondition> myLevelConditions;
    private Set<LevelCondition> myConditionsToRemove;

    public ConditionsManager(Set<LevelCondition> levelConditions) {
        myLevelConditions = levelConditions;
        myConditionsToRemove = new HashSet<>();
    }

    public Set<LevelAction> getActionsToExecute(Level level){
        Set<LevelAction> actionsToExecute = new HashSet<>();
        for (LevelCondition condition : myLevelConditions) {
            if (condition.hasHappened(level)) {
                myConditionsToRemove.add(condition);
                LevelAction action = condition.getAction();
                actionsToExecute.add(action);
            }
        }
        removePassedConditions();
        return actionsToExecute;
    }

    private void removePassedConditions() {
        for (LevelCondition condition : myConditionsToRemove){
            myLevelConditions.remove(condition);
        }
        myConditionsToRemove.clear();
    }
}
