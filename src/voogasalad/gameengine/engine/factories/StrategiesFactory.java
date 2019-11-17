package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
<<<<<<< HEAD
import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;
import voogasalad.gameengine.engine.spritestrategies.movement.MovementStrategy;
=======
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
>>>>>>> 697b3995706c5022cd262ae541ea99ec04322a9b

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class StrategiesFactory {

    private static final String CLASS_PATH = "voogasalad.gameengine.engine.sprites.strategies.";
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
<<<<<<< HEAD

    public MovementStrategy makeMovement(String movementStrategy, Map<String, Object> parameters) throws GameEngineException {
        try {
            return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch(Exception e) {
            e.printStackTrace(); //TODO: Temporary;
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }
}
=======
}
>>>>>>> 697b3995706c5022cd262ae541ea99ec04322a9b
