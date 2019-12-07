package voogasalad.gameengine.executors.sprites.strategies.health;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.HealthBuilder;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;

import java.util.HashMap;
import java.util.Map;

public class NoHealth implements HealthStrategy {


    public NoHealth(HealthBuilder healthBuilder) {

    }

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
        return new HealthBuilder().build();
    }
}
