package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class MovementBuilder implements StrategyBuilder {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.movement.";

    public static final double DEFAULT_DISTANCE = 100;

    private String myType;
    private double mySpeed;
    private LinkedList<Point2D.Double> myPath;
    private double myDistance;

    public MovementBuilder setType(String typeString) {
        myType = typeString.strip();
        return this;
    }

    public String getType() {
        return myType;
    }

    public MovementBuilder setPath(String pathString) {
        myPath = ConfigurationTool.convertPathStringToPath(pathString);
        return this;
    }

    public LinkedList<Point2D.Double> getPath() {
        return myPath;
    }

    public MovementBuilder setSpeed(String speedString) {
        System.out.println("Set speed string in movement builder:" + speedString);
        mySpeed = Double.parseDouble(speedString.strip());
        return this;
    }

    public MovementBuilder setDistance(String distanceString) {
        try {
            myDistance = Double.parseDouble(distanceString);
        } catch (NumberFormatException e) {
            myDistance = DEFAULT_DISTANCE;
        }
        return this;
    }

    public double getSpeed() {
        return mySpeed;
    }

    public double getDistance() {
        return myDistance;
    }

    @Override
    public MovementStrategy build() throws GameEngineException {
        try {
            return (MovementStrategy) Class.forName(CLASS_PATH + myType).getConstructor(MovementBuilder.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }
}
