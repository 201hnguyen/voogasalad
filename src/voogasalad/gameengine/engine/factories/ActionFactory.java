package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.action.GameAction;
import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.lang.reflect.InvocationTargetException;

public class ActionFactory {

    public GameAction makeGameAction(String action) throws GameEngineException {
        try {
            return (GameAction) Class.forName(action).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new GameEngineException(e, "ActionExecutionFailed");
        }
    }
}
