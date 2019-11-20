package voogasalad.gameengine.engine.gamecontrol.condition;

import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;

import java.util.Set;

public interface LevelCondition {
    boolean hasHappened(Level level);
    Set<LevelAction> getActions();
}
