package voogasalad.gameengine.engine.spritestrategies.health;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.utils.Verifier;

import java.util.Map;

public class Health implements HealthStrategy {
    private Integer myHealth;

    public Health(Map<String, Object> parameters) throws GameEngineException {
        myHealth = (Integer) Verifier.verifyAndGetStrategyParameter(parameters, "myHealth");
        System.out.println("Health value: " + myHealth);
    }

    @Override
    public Integer getHealth() {
        return myHealth;
    }

    @Override
    public void alterHealthByAddition(int value) {
        myHealth+=value;
    }
}
