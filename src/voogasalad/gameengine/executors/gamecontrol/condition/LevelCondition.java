package voogasalad.gameengine.executors.gamecontrol.condition;

import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;

import java.util.Set;
//TODO: Change level condition to abstract inheritance hierarchy instead of interface
public interface LevelCondition {
    boolean hasHappened(Level level);
    Set<LevelAction> getActions();
    ConditionClassification getClassification();
}
