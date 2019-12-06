package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.utils.Verifier;

import java.util.Map;

public class FullRotationStrategy implements RotationStrategy {
    private int rotationDirection;
    private Double rotationSpeed;
    private Map<String, Object> originalParameters;

    public FullRotationStrategy(Map<String, Object> parameters) {
        originalParameters = parameters;
        rotationSpeed = 3.0;
    }

    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        determineRotationDirection(currentAngle);
        double diffAngle = elapsedTime * rotationDirection * rotationSpeed;
        return (currentAngle + diffAngle) % 360;
    }

    @Override
    public void determineTargetAngle(Double currentAngle) {
        //no target angle
    }

    @Override
    public void determineRotationDirection(Double currentAngle) {
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
