package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.RotationBuilder;

public class FullRotation implements RotationStrategy {
    private int rotationDirection;
    private Double rotationSpeed;
    private RotationBuilder myBuilder;

    public FullRotation(RotationBuilder rotationBuilder) {
        myBuilder = rotationBuilder;
        rotationSpeed = rotationBuilder.getSpeed();
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
        return myBuilder.build();
    }
}
