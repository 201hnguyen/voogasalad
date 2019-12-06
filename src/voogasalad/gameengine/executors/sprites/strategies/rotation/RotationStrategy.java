package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface RotationStrategy {

    double updateAngle(double elapsedTime, double currentAngle);
    void determineTargetAngle(Double currentAngle);
    void determineRotationDirection(Double currentAngle);

    int getRotationDirection();
    RotationStrategy makeClone() throws GameEngineException;

}
