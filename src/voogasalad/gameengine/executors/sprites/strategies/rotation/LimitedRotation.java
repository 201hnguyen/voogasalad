package voogasalad.gameengine.executors.sprites.strategies.rotation;

import javafx.util.Pair;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.RotationBuilder;

public class LimitedRotation implements RotationStrategy {
    private Double targetAngle;
    private Double rotationSpeed;
    private Pair<Double, Double> validRotationRange;
    private int rotationDirection;
    RotationBuilder myBuilder;


    public LimitedRotation(RotationBuilder builder) {
        myBuilder = builder;
        rotationSpeed = myBuilder.getSpeed();
        validRotationRange = myBuilder.getRotationRange();
        targetAngle = validRotationRange.getKey();
    }

    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        determineTargetAngle(currentAngle);
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
        return myBuilder.build();
    }
}