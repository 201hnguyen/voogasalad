package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;

public class AttackBuilder implements StrategyBuilder {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.attack.";
    public static final String DEFAULT_TYPE = "NoAttack";

    private String myType;
    private Integer myBulletPrototypeId;
    private Double myAttackRate;
    private ArrayList<Double> myShootingPositions;

    public AttackBuilder setBulletPrototypeId(String bulletPrototypeId) throws GameEngineException {
        try {
            myBulletPrototypeId = Integer.parseInt(bulletPrototypeId);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
        return this;
    }

    public AttackBuilder setAttackRate(String attackRate) throws GameEngineException {
        try {
            myAttackRate = Double.parseDouble(attackRate);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
        return this;
    }

    public AttackBuilder setType(String type) {
        myType = type;
        return this;
    }

    public AttackBuilder setShootingPositions(String shootingPositionsString) throws GameEngineException {
        ArrayList<Double> gunsParsed = new ArrayList<Double>();
        try{
            String[] shootingPositionsSplit = shootingPositionsString.strip().split(",");
            for(String gun : shootingPositionsSplit){
                gunsParsed.add(Double.parseDouble(gun));
            }
            myShootingPositions = gunsParsed;
        } catch(NumberFormatException e){
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
        return this;
    }

    public Integer getBulletPrototypeId() {
        return myBulletPrototypeId;
    }

    public double getAttackRate() {
        return myAttackRate;
    }

    public String getType() {
        return myType;
    }

    public ArrayList<Double> getMyShootingPositions() {
        return myShootingPositions;
    }

    @Override
    public AttackStrategy build() throws GameEngineException {
        if (myType==null) {
            myType = DEFAULT_TYPE;
        }
        try{
            return (AttackStrategy) Class.forName(CLASS_PATH + myType).getConstructor(AttackBuilder.class).newInstance(this);
        } catch(Exception e){
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
    }
}
