package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.objectcreators.RotationBuilder;

public class NoRotation implements RotationStrategy { //TODO: can also be achieved by creating a fullrotation w speed 0

    public NoRotation(RotationBuilder builder) {
    }

    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        return currentAngle;
    }

    @Override
    public void determineTargetAngle(Double currentAngle) {
        //will never rotate!
    }

    @Override
    public void determineRotationDirection(Double currentAngle) {
        //will never rotate!
    }

    @Override
    public int getRotationDirection() {
        return 0; //will never rotate!
    }

    @Override
    public RotationStrategy makeClone() {
        return new NoRotation(new RotationBuilder());
    }
}
