package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.Verifier;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

public class PathMovement implements MovementStrategy {
    private Map<String, Object> myOriginalParameters;
    private LinkedList<Point> myPath;
    private Point nextPosition;
    private int nextPositionIndex;
    private boolean reachedEnd;
    private double mySpeed;
    private Point myDirection;


    public PathMovement(double speed, LinkedList<Point> path) throws GameEngineException {
        mySpeed = speed;
        myPath = path;
        nextPositionIndex = 0;
        if(myPath.size() < 1) {
            reachedEnd = true;
        } else {
            reachedEnd = false;
            nextPosition = myPath.get(nextPositionIndex);
        }
    }

    @Override
    public MovementStrategy makeClone() throws GameEngineException {
        return new PathMovement(mySpeed, myPath);
    }

    @Override
    public Point calculateNextPosition(double elapsedTime, Point currentPosition) {
        if(reachedEnd) {
            return currentPosition;
        } else if (myDirection == null){
            myDirection = calculateDirection(currentPosition);
        }
        double diffX = myDirection.getX() * elapsedTime;
        double diffY = myDirection.getY() * elapsedTime;
        double updatedX = currentPosition.getX() + diffX;
        double updatedY = currentPosition.getY() + diffY;
        Point updatedPosition = new Point();
        updatedPosition.setLocation(updatedX, updatedY);
        if(checkDirectionChange(currentPosition, updatedPosition)) {
            Point toReturn = new Point();
            toReturn.setLocation(nextPosition.getX(), nextPosition.getY());
            changeDirection();
            return toReturn;
        } else {
            return updatedPosition;
        }
    }

    private Point calculateDirection(Point currentPosition) {
        Point updatedDirection = new Point();
        double diffX = nextPosition.getX() - currentPosition.getX();
        double diffY = nextPosition.getY() - currentPosition.getY();
        double hypotenuse = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        double ratio = mySpeed / hypotenuse;
        double updatedX = diffX * ratio;
        double updatedY = diffY * ratio;
        updatedDirection.setLocation(updatedX, updatedY);
        return updatedDirection;
    }

    private boolean checkDirectionChange(Point currentPosition, Point updatedPosition) {
        boolean passedX = checkInRange(nextPosition.getX(), currentPosition.getX(), updatedPosition.getX());
        boolean passedY = checkInRange(nextPosition.getY(), currentPosition.getY(), updatedPosition.getY());
        return passedX && passedY;
    }

    private boolean checkInRange(double x, double bound1, double bound2) {
        return ((x - bound1) * (x - bound2) <= 0);
    }

    private void changeDirection() {
        if(nextPositionIndex + 1 < myPath.size()) {
            nextPositionIndex++;
            Point origin = new Point();
            origin.setLocation(nextPosition.getX(), nextPosition.getY());
            nextPosition = myPath.get(nextPositionIndex);
            myDirection = calculateDirection(origin);
        } else {
            reachedEnd = true;
        }
    }
}
