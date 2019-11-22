package voogasalad.gameengine.executors.gamecontrol.managers;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ConditionsManager {
    private Set<LevelCondition> myLevelConditions;

    public ConditionsManager() {
        myLevelConditions = new HashSet<>();
    }

    public void addConditionCollection(Collection<LevelCondition> levelConditions) {
        myLevelConditions.addAll(levelConditions);
    }

    public Set<LevelAction> getActionsToExecute(Level level){
        Set<LevelCondition> conditionsToRemove = new HashSet<>();
        Set<LevelAction> actionsToExecute = new HashSet<>();
        myLevelConditions.stream()
                .filter(condition -> condition.hasHappened(level))
                .forEach(condition -> { conditionsToRemove.add(condition); actionsToExecute.addAll(condition.getActions()); });
        conditionsToRemove.stream().
                forEach(condition -> myLevelConditions.remove(condition));
        return actionsToExecute;
    }
}
