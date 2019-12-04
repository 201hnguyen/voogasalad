package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class StrategiesFactory {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.";
    private static final String HEALTH_DIRECTORY = "health.";
    private static final String MOVEMENT_DIRECTORY = "movement.";

    public HealthStrategy makeHealth(String healthStrategy, Map<String, Object> parameters) throws GameEngineException {
        try {
            return (HealthStrategy) Class.forName(CLASS_PATH + HEALTH_DIRECTORY + healthStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
    }

    public MovementStrategy makeMovement(String movementStrategy, Map<String, Object> parameters) throws GameEngineException {
        try {
            return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch(Exception e) {
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }
}
