package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.utils.Verifier;

import java.util.Map;

public class FullRotationStrategy implements RotationStrategy {
    private Double currentAngle;
    private int rotationDirection;
    private Double rotationSpeed;
    private Map<String, Object> originalParameters;

    public FullRotationStrategy(Map<String, Object> parameters) throws GameEngineException {
        originalParameters = parameters;
        currentAngle = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myAngle");
        rotationSpeed = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myRotationSpeed");
    }

    @Override
    public void updateAngle(double elapsedTime) {
        determineRotationDirection();
        System.out.println("My current direction is " + rotationDirection); //TODO: DEBUGGING ONLY
        double diffAngle = elapsedTime * rotationDirection * rotationSpeed;
        currentAngle = (currentAngle + diffAngle) % 360;
    }

    @Override
    public Double getCurrentAngle() {
        return currentAngle;
    }

    @Override
    public void determineTargetAngle() {
        //no target angle
    }

    @Override
    public void determineRotationDirection() {
        rotationDirection = 1;
    }

    @Override
    public int getRotationDirection(){ //for testing only
        return rotationDirection;
    }

    @Override
    public RotationStrategy makeClone() throws GameEngineException {
        StrategiesFactory factory = new StrategiesFactory();
        return factory.makeRotation("FullRotationStrategy", originalParameters);
    }
}