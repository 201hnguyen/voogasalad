package voogasalad.gameengine.engine.conditions;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.exceptions.GameEngineException;

public interface GameCondition {
    boolean isMet(Level level);
    void executeAction(Level level) throws GameEngineException;
}
