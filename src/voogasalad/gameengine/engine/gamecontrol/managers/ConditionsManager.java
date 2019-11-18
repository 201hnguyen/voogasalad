package voogasalad.gameengine.engine.gamecontrol.managers;

import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;

import java.util.HashSet;
import java.util.Set;

public class ConditionsManager {
    private Set<LevelCondition> myLevelConditions;

    public ConditionsManager(Set<LevelCondition> levelConditions) {
        myLevelConditions = levelConditions;
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
