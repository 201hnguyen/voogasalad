package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;

import java.awt.Point;
import java.util.LinkedList;

public class MovementBuilder {
    private String movementType;
    private double mySpeed;
    private LinkedList<Point> myPath;

    public MovementBuilder setMovementType(String typeString) {
        movementType = typeString.strip();
        return this;
    }

    public String getMovementType() {
        return movementType;
    }

    public MovementBuilder setPath(String pathString) {
        System.out.println("Set path string in movement builder:" + pathString);
        LinkedList<Point> parsedPath = new LinkedList<>();
        String[] pointStrings = pathString.strip().split(";");
        for(String pointString : pointStrings) {
            Point toAdd = new Point();
            String[] coordinateStrings = pointString.split(",");
            toAdd.setLocation(Double.parseDouble(coordinateStrings[0]), Double.parseDouble(coordinateStrings[1]));
            parsedPath.add(toAdd);
        }
        myPath = parsedPath;
        return this;
    }

    public LinkedList<Point> getPath() {
        return myPath;
    }

    public MovementBuilder setSpeed(String speedString) {
        System.out.println("Set speed string in movement builder:" + speedString);
        mySpeed = Double.parseDouble(speedString.strip());
        return this;
    }

    public double getSpeed() {
        return mySpeed;
    }


    public MovementStrategy build() throws GameEngineException {
        StrategiesFactory movementStrategyFactory = new StrategiesFactory();
        return movementStrategyFactory.makeMovement(this);
    }
}
