package voogasalad.gameengine.executors.sprites.strategies.rotation;

import javafx.util.Pair;
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
        System.out.println("Testing limited rotation:" + rotationDirection + "current angle: " + currentAngle + "target angle:" + targetAngle);
    }

    @Override
    public int getRotationDirection() {
        return rotationDirection;
    }

    @Override
    public RotationStrategy makeClone() {
        return new LimitedRotation(myBuilder);
    }
}