package voogasalad.gameengine.executors.sprites.strategies.health;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface HealthStrategy {
    Integer getHealth();
    void alterHealthByAddition(int value);
    HealthStrategy makeClone() throws GameEngineException;
}
