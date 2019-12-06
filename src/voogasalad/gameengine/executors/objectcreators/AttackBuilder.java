package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;

public class AttackBuilder {

    public static final String DEFAULT_TYPE = "NoAttack";
    public static final Integer DEFAULT_BULLET_PROTOTYPE_ID = 0; //FIXME
    public static final Double DEFAULT_ATTACK_SPEED = 0.0;

    private String myType;
    private Integer myBulletPrototypeId;
    private Double myAttackRate;

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

    public Integer getBulletPrototypeId() {
        return myBulletPrototypeId;
    }

    public double getAttackRate() {
        return myAttackRate;
    }

    public String getType() {
        return myType;
    }

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
        return new StrategiesFactory().makeAttack(this);
    }
}
