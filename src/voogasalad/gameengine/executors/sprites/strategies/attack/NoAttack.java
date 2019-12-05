package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;

import java.util.Map;

public class NoAttack implements AttackStrategy {

    private Map<String, Object> originalParameters;

    public NoAttack(Map<String, Object> parameters){
        originalParameters = parameters;
    }

    @Override
    public void attack(double elapsedTime) {
        //will never attack
    }

    @Override
    public Double getAttackRate() {
        return 0.0; //no attack rate
    }

    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        StrategiesFactory factory = new StrategiesFactory();
        return factory.makeAttack("NoAttack", originalParameters);
    }
}
