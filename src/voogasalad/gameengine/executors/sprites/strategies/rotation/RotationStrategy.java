package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface RotationStrategy {

    double updateAngle(double elapsedTime, double currentAngle);
    RotationStrategy makeClone() throws GameEngineException;

}