package voogasalad.gameengine.engine.action.auto;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.exceptions.GameEngineException;

public interface GameAutoAction {
    void executeAction(Level level) throws GameEngineException;
}
