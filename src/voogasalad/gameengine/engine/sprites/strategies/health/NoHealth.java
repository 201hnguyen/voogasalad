package voogasalad.gameengine.engine.sprites.strategies.health;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.StrategiesFactory;

import java.util.HashMap;
import java.util.Map;

public class NoHealth implements HealthStrategy {

    public NoHealth(Map<String, Object> parameters) {}

    @Override
    public Integer getHealth() {
        return null;
    }

    @Override
    public void alterHealthByAddition(int value) {
        // do nothing
    }

    @Override
    public HealthStrategy makeClone() throws GameEngineException {
        StrategiesFactory strategiesFactory = new StrategiesFactory();
        return strategiesFactory.makeHealth(getClass().getName(), new HashMap<>());
    }
}
