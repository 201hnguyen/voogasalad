package voogasalad.gameengine.executors.sprites.strategies.rotation;

import javafx.util.Pair;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.utils.Verifier;

import java.util.Map;

public class LimitedRotationStrategy implements RotationStrategy {
    private Double targetAngle;
    private Double rotationSpeed;
    private Pair<Double, Double> validRotationRange;
    private int rotationDirection;
    Map<String, Object> originalParameters;


    public LimitedRotationStrategy(Map<String, Object> parameters) throws GameEngineException {
        originalParameters = parameters;
        rotationSpeed = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myRotationSpeed");
        validRotationRange = (Pair<Double, Double>) Verifier.verifyAndGetStrategyParameter(parameters, "myRotationRange");
        targetAngle = validRotationRange.getKey();
    }

    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        determineRotationDirection(currentAngle);
        double diffAngle = rotationDirection * elapsedTime * rotationSpeed;
        return currentAngle + diffAngle;
    }

    @Override
    public void determineTargetAngle(Double currentAngle) {
        Double lowerBound = validRotationRange.getKey();
        Double upperBound = validRotationRange.getValue();
        if (currentAngle >= upperBound){
            targetAngle = lowerBound;
        }
        if (currentAngle <= lowerBound){
            targetAngle = upperBound;
        }
    }

    @Override
    public void determineRotationDirection(Double currentAngle) {
        determineTargetAngle(currentAngle);
        if (currentAngle.equals(targetAngle)) {
            rotationDirection = 0;
        } else if (currentAngle > targetAngle) {
            rotationDirection = -1;
        } else {
            rotationDirection = 1;
        }
    }

    @Override
    public int getRotationDirection() {
        return rotationDirection;
    }

    @Override
    public RotationStrategy makeClone() throws GameEngineException {
        StrategiesFactory factory = new StrategiesFactory();
        return factory.makeRotation("LimitedRotationStrategy", originalParameters);
    }
}