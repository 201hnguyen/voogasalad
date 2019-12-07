package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Map;

public class StrategiesFactory {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.";
    private static final String HEALTH_DIRECTORY = "health.";
    private static final String MOVEMENT_DIRECTORY = "movement.";
    private static final String ROTATION_DIRECTORY = "rotation.";
    private static final String ATTACK_DIRECTORY = "attack.";

    public HealthStrategy makeHealth(HealthBuilder healthBuilder) throws GameEngineException {
        try {
            return (HealthStrategy) Class.forName(CLASS_PATH + HEALTH_DIRECTORY + healthBuilder.getType()).getConstructor(HealthBuilder.class).newInstance(healthBuilder);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
    }

    public MovementStrategy makeMovement(MovementBuilder movementBuilder) throws GameEngineException {
        try {
            return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementBuilder).getConstructor(MovementBuilder.class).newInstance(movementBuilder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }

    public RotationStrategy makeRotation(RotationBuilder rotationBuilder) throws GameEngineException{
        try{
            return (RotationStrategy) Class.forName(CLASS_PATH + ROTATION_DIRECTORY + rotationBuilder.getType()).getConstructor(RotationBuilder.class).newInstance(rotationBuilder);
        } catch(Exception e){
            e.printStackTrace(); //TODO: debugging only
            throw new GameEngineException(e, "SpriteRotationInitializationFailed");
        }
    }

    public AttackStrategy makeAttack(AttackBuilder attackBuilder) throws GameEngineException {
        try{
            return (AttackStrategy) Class.forName(CLASS_PATH + ATTACK_DIRECTORY + attackBuilder.getType()).getConstructor(AttackBuilder.class).newInstance(attackBuilder);
        } catch(Exception e){
            e.printStackTrace(); //TODO: debugging only
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
    }
}
