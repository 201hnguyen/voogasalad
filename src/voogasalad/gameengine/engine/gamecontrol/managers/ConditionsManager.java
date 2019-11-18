package voogasalad.gameengine.engine.gamecontrol.managers;

import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;

import java.util.HashSet;
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
        myLevelConditions.stream()
                .filter(condition -> condition.hasHappened(level))
                .forEach(condition -> { myConditionsToRemove.add(condition); actionsToExecute.addAll(condition.getActions()); });
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
