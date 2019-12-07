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

    public MovementStrategy makeMovement(MovementBuilder builder) throws GameEngineException {
        String movementType = builder.getMovementType();
        try {
            if (movementType.equalsIgnoreCase("PathMovement")) {
                return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementType).getConstructor(double.class, LinkedList.class).newInstance(builder.getSpeed(), builder.getPath());
            } else if (movementType.equalsIgnoreCase("NoMovement")) {
                return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementType).getConstructor().newInstance();
            } else if (movementType.equalsIgnoreCase("DirectedDistanceMovement")) {
                return (MovementStrategy) Class.forName(CLASS_PATH + MOVEMENT_DIRECTORY + movementType).getConstructor(MovementBuilder.class).newInstance(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
        return null;
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
