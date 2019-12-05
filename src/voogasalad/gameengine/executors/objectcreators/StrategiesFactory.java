package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class StrategiesFactory {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.";
    private static final String HEALTH_DIRECTORY = "health.";
    private static final String MOVEMENT_DIRECTORY = "movement.";
    private static final String ROTATION_DIRECTORY = "rotation.";
    private static final String ATTACK_DIRECTORY = "attack.";

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

    public RotationStrategy makeRotation(String rotationStrategy, Map<String, Object> parameters) throws GameEngineException{
        try{
            return (RotationStrategy) Class.forName(CLASS_PATH + ROTATION_DIRECTORY + rotationStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch(Exception e){
            e.printStackTrace(); //TODO: debugging only
            throw new GameEngineException(e, "SpriteRotationInitializationFailed");
        }
    }

    public AttackStrategy makeAttack(String attackStrategy, Map<String, Object> parameters) throws GameEngineException {
        try{
            return (AttackStrategy) Class.forName(CLASS_PATH + ATTACK_DIRECTORY + attackStrategy).getConstructor(Map.class).newInstance(parameters);
        } catch(Exception e){
            e.printStackTrace(); //TODO: debugging only
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
    }
}
