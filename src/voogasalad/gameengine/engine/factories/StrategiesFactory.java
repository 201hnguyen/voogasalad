package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;
import voogasalad.gameengine.engine.spritestrategies.movement.MovementStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class StrategiesFactory {

    private static final String CLASS_PATH = "voogasalad.gameengine.engine.spritestrategies.";
    private static final String HEALTH_DIRECTORY = "health.";
    private static final String MOVEMENT_DIRECTORY = "movement.";

    public HealthStrategy makeHealth(String healthStrategy, Map<String, Object> parameters) throws GameEngineException {
        try {
            return (HealthStrategy) Class.forName(CLASS_PATH + HEALTH_DIRECTORY + healthStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace(); //TODO: Delete; currently here so we can see what is going on.
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
    }

    public MovementStrategy makeMovement(String movementStrategy, Map<String, Object> parameters) throws GameEngineException {
        try {
            return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch(Exception e) {
            e.printStackTrace(); //TODO: Temporary;
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }
}
