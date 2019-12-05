package voogasalad.gameengine.executors.control.levelcontrol.managers;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LevelConditionsManager {
    private Set<LevelCondition> myLevelConditions;

    public LevelConditionsManager() {
        myLevelConditions = new HashSet<>();
    }

    public void addLevelConditionsCollection(Collection<LevelCondition> levelConditions) {
        myLevelConditions.addAll(levelConditions);
    }

    public Set<LevelAction> getLevelActionsToExecute(Level level){
        Set<LevelCondition> conditionsToRemove = new HashSet<>();
        Set<LevelAction> actionsToExecute = new HashSet<>();
        for (LevelCondition condition : myLevelConditions) {
            if (condition.hasHappened(level)) {
                actionsToExecute.addAll(condition.getLevelActions());
                if (condition.getClassification()==ConditionClassification.ONETIME) {
                    conditionsToRemove.add(condition);
                }
            }
        }

        conditionsToRemove.stream().
                forEach(condition -> myLevelConditions.remove(condition));
        return actionsToExecute;
    }
}
