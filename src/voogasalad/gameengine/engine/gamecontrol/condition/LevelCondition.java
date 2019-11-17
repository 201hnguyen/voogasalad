package voogasalad.gameengine.engine.gamecontrol.condition;

import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;

public interface LevelCondition {
    boolean hasHappened(Level level);
    LevelAction getAction();
}
