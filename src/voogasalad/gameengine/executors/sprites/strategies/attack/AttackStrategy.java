package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface AttackStrategy {
    void attack(double elapsedTime) throws GameEngineException;

    Double getAttackRate();

    AttackStrategy makeClone() throws GameEngineException;
}