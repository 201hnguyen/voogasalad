package voogasalad.gameengine.executors.sprites.strategies.cost;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.CostBuilder;

public class BasicCost implements CostStrategy {

    private int myCreateCost;
    private int myDestroyCost;
    private CostBuilder myBuilder;

     public BasicCost(CostBuilder costBuilder) {
         myBuilder = costBuilder;
         myCreateCost = myBuilder.getCreateCost();
         myDestroyCost = myBuilder.getDestroyCost();
     }

    @Override
    public int getCreateCost() {
        return myCreateCost;
    }

    @Override
    public int getDestroyCost() {
        return myDestroyCost;
    }

    @Override
    public CostStrategy makeClone() throws GameEngineException {
        return myBuilder.build() ;
    }
}
