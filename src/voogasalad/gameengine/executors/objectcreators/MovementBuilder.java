package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovementBuilder implements StrategyBuilder {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.movement.";

    public static final double DEFAULT_DISTANCE = 100;

    private String myType;
    private double mySpeed;
    private List<Point2D.Double> myPath;
    private double myDistance;

    public MovementBuilder setType(String typeString) {
        myType = typeString.strip();
        return this;
    }

    public String getType() {
        return myType;
    }

    public MovementBuilder setPath(String pathString) {
        System.out.println("Set path string in movement builder:" + pathString);
        List<Point2D.Double> parsedPath = new ArrayList<>();
        String[] pointStrings = pathString.strip().split(";");
        for(String pointString : pointStrings) {
            Point2D.Double toAdd = new Point2D.Double();
            String[] coordinateStrings = pointString.split(",");
            toAdd.setLocation(Double.parseDouble(coordinateStrings[0]), Double.parseDouble(coordinateStrings[1]));
            parsedPath.add(toAdd);
        }
        myPath = parsedPath;
        return this;
    }

    public List<Point2D.Double> getPath() {
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
