package voogasalad.gameengine.executors.objectcreators;

import javafx.util.Pair;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

public class RotationBuilder implements StrategyBuilder {
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.rotation.";

    public static final Pair<Double, Double> DEFAULT_ROTATION_RANGE = new Pair(0.0, 360.0);
    public static final Double DEFAULT_SPEED = 50.0;
    public static final String DEFAULT_TYPE = "NoRotation";

    private Double mySpeed;
    private Pair<Double, Double> myValidRotationRange;
    private String myType;

    public RotationBuilder setSpeed(String speedString) {
        try {
            mySpeed = Double.parseDouble(speedString);
        } catch (NumberFormatException e) {
            mySpeed = DEFAULT_SPEED;
        }
        return this;
    }

    public RotationBuilder setType(String typeString) {
        myType = typeString;
        return this;
    }

    public RotationBuilder setValidRotationRange(String rotationRangeString) {
        try {
            String[] range = rotationRangeString.split(",");
            Double minAngle = Double.parseDouble(range[0]);
            Double maxAngle = Double.parseDouble(range[1]);
            myValidRotationRange = new Pair(minAngle, maxAngle);
        } catch (NumberFormatException e) {
            myValidRotationRange = DEFAULT_ROTATION_RANGE;
        }
        return this;
    }

    public Pair<Double, Double> getRotationRange() {
        return myValidRotationRange;
    }

    public Double getSpeed() {
        return mySpeed;
    }

    public String getType() {
        return myType;
    }

    public RotationStrategy build() throws GameEngineException {
        if (myType == null) {
            myType = DEFAULT_TYPE;
        }
        if (myValidRotationRange == null) {
            myValidRotationRange = DEFAULT_ROTATION_RANGE;
        }
        if (mySpeed == null) {
            mySpeed = DEFAULT_SPEED;
        }
        try{
            return (RotationStrategy) Class.forName(CLASS_PATH + myType).getConstructor(RotationBuilder.class).newInstance(this);
        } catch(Exception e){
            e.printStackTrace(); //TODO: debugging only
            throw new GameEngineException(e, "SpriteRotationInitializationFailed");
        }
    }

}
