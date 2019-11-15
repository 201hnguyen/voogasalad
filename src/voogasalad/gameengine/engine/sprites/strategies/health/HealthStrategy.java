package voogasalad.gameengine.engine.sprites.strategies.health;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.util.Map;

public interface HealthStrategy {
    Integer getHealth();
    void alterHealthByAddition(int value);
    HealthStrategy makeClone() throws GameEngineException;
}
