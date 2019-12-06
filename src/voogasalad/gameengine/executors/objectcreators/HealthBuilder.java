package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;

public class HealthBuilder {
    public static final int DEFAULT_HEALTH_VALUE=0;
    public static final String DEFAULT_TYPE="NoHealth";

    private int myHealthValue;
    private String myType;

    public HealthBuilder setHealthType(String type) {
        myType = type;
        return this;
    }

    public HealthBuilder setHealthValue(String healthValue) {
        try {
            myHealthValue = Integer.parseInt(healthValue);
        } catch (NumberFormatException e) {
            myHealthValue = DEFAULT_HEALTH_VALUE;
        }
        return this;
    }

    public String getType() {
        return myType;
    }

    public int getHealthValue() {
        return myHealthValue;
    }

    public HealthStrategy build() throws GameEngineException {
        if (myType==null) {
            myType=DEFAULT_TYPE;
        }
        return new StrategiesFactory().makeHealth(this);
    }

}
