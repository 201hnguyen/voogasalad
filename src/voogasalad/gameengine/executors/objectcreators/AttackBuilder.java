package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class AttackBuilder implements StrategyBuilder {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.attack.";


    public static final String DEFAULT_TYPE = "NoAttack";
    public static final Integer DEFAULT_BULLET_PROTOTYPE_ID = 0; //FIXME
    public static final Double DEFAULT_ATTACK_SPEED = 0.0;

    private String myType;
    private Integer myBulletPrototypeId;
    private Double myAttackRate;
    private ArrayList<Double> myShootingPositions;

    public AttackBuilder setBulletPrototypeId(String bulletPrototypeId) {
        try {
            myBulletPrototypeId = Integer.parseInt(bulletPrototypeId);
        } catch (NumberFormatException e) {
            myBulletPrototypeId = null;
        }
        System.out.println("testing builder bullet id: " + myBulletPrototypeId);
        return this;
    }

    public AttackBuilder setAttackRate(String attackRate) {
        try {
            myAttackRate = Double.parseDouble(attackRate);
        } catch (NumberFormatException e) {
            myAttackRate = null;
        }
        System.out.println("testing builder attack rate: " + myAttackRate);
        return this;
    }

    public AttackBuilder setType(String type) {
        myType = type;
        return this;
    }

    public AttackBuilder setShootingPositions(String shootingPositionsString){
        ArrayList<Double> gunsParsed = new ArrayList<Double>();
        System.out.println("set shooting positions in attack builder: " + shootingPositionsString);
        try{
            String[] shootingPositionsSplit = shootingPositionsString.strip().split(",");
            for(String gun : shootingPositionsSplit){
                gunsParsed.add(Double.parseDouble(gun));
                System.out.println("valid shooting pos: " + gun);
            }
            myShootingPositions = gunsParsed;
        }catch(NumberFormatException e){
            myShootingPositions = null;
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
        if (myAttackRate ==null) {
            myAttackRate = DEFAULT_ATTACK_SPEED;
        }
        if (myBulletPrototypeId == null) {
            myBulletPrototypeId = DEFAULT_BULLET_PROTOTYPE_ID;
        }
        if (myShootingPositions == null){
            myShootingPositions = new ArrayList<>();
        }
        try{
            return (AttackStrategy) Class.forName(CLASS_PATH + myType).getConstructor(AttackBuilder.class).newInstance(this);
        } catch(Exception e){
            e.printStackTrace(); //TODO: debugging only
            throw new GameEngineException(e, "SpriteAttackInitializationFailed");
        }
    }
}
