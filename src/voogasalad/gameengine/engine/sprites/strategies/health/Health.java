package voogasalad.gameengine.engine.sprites.strategies.health;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.utils.Verifier;

import java.util.Map;

public class Health implements HealthStrategy {
    private Integer myHealth;
    private Map<String, Object> myOriginalParameters;

    public Health(Map<String, Object> parameters) throws GameEngineException {
        myOriginalParameters = parameters;
        myHealth = (Integer) Verifier.verifyAndGetStrategyParameter(parameters, "myHealth");
    }

    @Override
    public Integer getHealth() {
        return myHealth;
    }

    @Override
    public void alterHealthByAddition(int value) {
        myHealth+=value;
    }

    @Override
    public HealthStrategy makeClone() throws GameEngineException {
        StrategiesFactory strategiesFactory = new StrategiesFactory();
        return strategiesFactory.makeHealth("Health", myOriginalParameters);
    }
}
