package voogasalad.gameengine.engine.sprites.strategies.movement;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.utils.Verifier;

import java.awt.*;
import java.util.HashMap;
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


    public PathMovement(Map<String, Object> parameters) throws GameEngineException {
        myOriginalParameters = parameters;
        mySpeed = (double) Verifier.verifyAndGetStrategyParameter(parameters, "mySpeed");
        myPath = (LinkedList<Point>) Verifier.verifyAndGetStrategyParameter(parameters, "myPath");
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
        return new PathMovement(myOriginalParameters);
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
        if(checkDirectionChange(updatedPosition)) {
            Point toReturn = nextPosition;
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

    private boolean checkDirectionChange(Point updatedPosition) {
        boolean passedX;
        boolean passedY;

        if(myDirection.getX() < 0) {
            passedX = updatedPosition.getX() <= nextPosition.getX();
        } else if(myDirection.getX() == 0) {
            passedX = true;
        } else {
            passedX = updatedPosition.getX() >= nextPosition.getX();
        }

        if(myDirection.getY() < 0) {
            passedY = updatedPosition.getY() <= nextPosition.getY();
        } else if(myDirection.getY() == 0) {
            passedY = true;
        } else {
            passedY = updatedPosition.getY() >= nextPosition.getY();
        }

        return passedX && passedY;
    }

    private void changeDirection() {
        if(nextPositionIndex + 1 < myPath.size()) {
            nextPositionIndex++;
            Point origin = nextPosition;
            nextPosition = myPath.get(nextPositionIndex);
            calculateDirection(origin);
        } else {
            reachedEnd = true;
        }
    }
}
