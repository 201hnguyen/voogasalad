package voogasalad.gameengine.executors.sprites.strategies.health;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.HealthBuilder;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.utils.Verifier;

import java.util.Map;

public class Health implements HealthStrategy {
    private Integer myHealth;
    private HealthBuilder myHealthBuilder;

    public Health(HealthBuilder healthBuilder) {
        myHealthBuilder = healthBuilder;
        myHealth = myHealthBuilder.getHealthValue();
    }

    @Override
    public Integer getHealth() {
        return myHealth;
    }

    @Override
    public void addHealth(int value) {
        myHealth += value;
    }

    @Override
    public void chunkHealth(int value) {
        myHealth -= value;
    }

    @Override
    public HealthStrategy makeClone() throws GameEngineException {
        return myHealthBuilder.build();
    }
}
