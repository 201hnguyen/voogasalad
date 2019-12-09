package voogasalad.gameengine.executors.sprites.strategies.cost;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

public interface CostStrategy {
    int getCreateCost();
    int getDestroyCost();
    CostStrategy makeClone() throws GameEngineException;
}
