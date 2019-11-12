package voogasalad.gameengine.engine.action;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.exceptions.GameEngineException;

public interface GameAction {
    void executeAction(Level level) throws GameEngineException;
}
