package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.utils.Verifier;

import java.util.Map;

public class NoRotationStrategy implements RotationStrategy { //TODO: can also be achieved by creating a fullrotation w speed 0
    private Map<String, Object> originalParameters;

    public NoRotationStrategy(Map<String, Object> parameters) throws GameEngineException {
        originalParameters = parameters;
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
    public RotationStrategy makeClone() throws GameEngineException {
        StrategiesFactory factory = new StrategiesFactory();
        return factory.makeRotation("NoRotationStrategy", originalParameters);
    }
}
