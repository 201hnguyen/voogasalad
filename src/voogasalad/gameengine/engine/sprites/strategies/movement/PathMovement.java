package voogasalad.gameengine.engine.sprites.strategies.movement;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.utils.Verifier;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

public class PathMovement implements MovementStrategy {
    private LinkedList<Point> myPath;
    private Point currentPosition;
    private Point nextPosition;
    private int nextPositionIndex;
    private boolean reachedEnd;
    private double mySpeed;
    private Point myDirection;


    public PathMovement(Map<String, Object> parameters) throws GameEngineException {
        mySpeed = (double) Verifier.verifyAndGetStrategyParameter(parameters, "mySpeed");
        myPath = (LinkedList<Point>) Verifier.verifyAndGetStrategyParameter(parameters, "myPath");
        nextPositionIndex = 1;
        currentPosition = myPath.getFirst();
        if(nextPositionIndex < myPath.size()) {
            reachedEnd = false;
            nextPosition = myPath.get(nextPositionIndex);
            myDirection = calculateDirection();
        } else {
            reachedEnd = true;
        }
    }


    @Override
    public Point getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void updatePosition(double elapsedTime) {
        if(reachedEnd) {
            return;
        }
        double diffX = myDirection.getX() * elapsedTime;
        double diffY = myDirection.getY() * elapsedTime;
        double updatedX = currentPosition.getX() + diffX;
        double updatedY = currentPosition.getY() + diffY;
        Point updatedPosition = new Point();
        updatedPosition.setLocation(updatedX, updatedY);
        if(checkDirectionChange(updatedPosition)) {
            System.out.println("hello");
            changeDirection();
        } else {
            currentPosition = updatedPosition;
        }
    }

    private Point calculateDirection() {
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
            passedX = currentPosition.getX() <= nextPosition.getX();
        } else {
            passedX = currentPosition.getX() >= nextPosition.getX();
        }
        if(myDirection.getY() < 0) {
            passedY = currentPosition.getY() <= nextPosition.getY();
        } else {
            passedY = currentPosition.getY() >= nextPosition.getY();
        }
        return passedX && passedY;
    }

    private void changeDirection() {
        if(nextPositionIndex + 1 < myPath.size()) {
            nextPositionIndex++;
            currentPosition = nextPosition;
            nextPosition = myPath.get(nextPositionIndex);
            calculateDirection();
        } else {
            currentPosition = myPath.getLast();
            reachedEnd = true;
        }
    }
}
