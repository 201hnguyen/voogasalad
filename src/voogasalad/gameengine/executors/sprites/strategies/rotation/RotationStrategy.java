package voogasalad.gameengine.executors.sprites.strategies.rotation;

        import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface RotationStrategy {

    void updateAngle(double elapsedTime);
    Double getCurrentAngle();
    void determineTargetAngle();
    void determineRotationDirection();

    int getRotationDirection();
    RotationStrategy makeClone() throws GameEngineException;

}
