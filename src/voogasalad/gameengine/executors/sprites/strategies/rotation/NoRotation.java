package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.objectcreators.RotationBuilder;

public class NoRotation implements RotationStrategy {

    public NoRotation(RotationBuilder builder) {
    }

    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        return currentAngle;
    }

    @Override
    public RotationStrategy makeClone() {
        return new NoRotation(new RotationBuilder());
    }
}
