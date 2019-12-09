package voogasalad.gameengine.executors.sprites.strategies.cost;

public interface CostStrategy {
    int getCreateCost();
    int getDestroyCost();
    CostStrategy makeClone();
}
