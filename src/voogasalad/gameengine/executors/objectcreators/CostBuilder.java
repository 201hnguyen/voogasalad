package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.cost.CostStrategy;

import java.lang.reflect.InvocationTargetException;

public class CostBuilder implements StrategyBuilder{

    public static int DEFAULT_CREATE_COST = 0;
    public static int DEFAULT_DESTROY_COST = 0;
    public static String DEFAULT_TYPE = "BasicCost";
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.cost.";

    private int myCreateCost;
    private int myDestroyCost;
    private String myType;


    public CostBuilder setCost(String cost) {
        setCreateCost(cost);
        setDestroyCost(cost);
        return this;
    }

    public CostBuilder setType(String type) {
        myType = type;
        return this;
    }

    public CostBuilder setCreateCost(String cost) {
        try {
            myCreateCost = Integer.parseInt(cost);
        } catch (NumberFormatException e) {
            myCreateCost = DEFAULT_CREATE_COST;
        }
        return this;
    }

    public CostBuilder setDestroyCost(String cost) {
        try {
            myDestroyCost = Integer.parseInt(cost);
        } catch (NumberFormatException e) {
            myDestroyCost = DEFAULT_DESTROY_COST;
        }
        return this;
    }

    public int getCreateCost() {
        return myCreateCost;
    }

    public int getDestroyCost() {
        return myDestroyCost;
    }

    @Override
    public CostStrategy build() throws GameEngineException {
        if (myType == null) {
            myType = DEFAULT_TYPE;
        }
        try {
            return (CostStrategy) Class.forName(CLASS_PATH + myType).getConstructor(CostBuilder.class).newInstance(this);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }
}
