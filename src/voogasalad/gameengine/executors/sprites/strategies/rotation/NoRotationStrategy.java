package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.utils.Verifier;

import java.util.Map;

public class NoRotationStrategy implements RotationStrategy { //TODO: can also be achieved by creating a fullrotation w speed 0
    private Double currentAngle;
    private Map<String, Object> originalParameters;

    public NoRotationStrategy(Map<String, Object> parameters) throws GameEngineException {
        originalParameters = parameters;
        currentAngle = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myAngle");
    }

    @Override
    public Double getCurrentAngle() {
        return currentAngle;
    }

    @Override
    public void updateAngle(double elapsedTime) {
        //will never rotate!
    }

    @Override
    public void determineTargetAngle() {
        //will never rotate!
    }

    @Override
    public void determineRotationDirection() {
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
