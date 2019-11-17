package voogasalad.gameengine.engine.sprites.strategies.movement;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.utils.Verifier;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

public class PathMovement implements MovementStrategy {
    public static final double DIRECTION_CHANGE_THRESHOLD = 0.1;

    private LinkedList<Point> myPath;
    private Point currentPosition;
    private Point nextPosition;
    private int positionIndex;
    private double mySpeed;
    private Point myDirection;


    public PathMovement(Map<String, Object> parameters) throws GameEngineException {
        mySpeed = (double) Verifier.verifyAndGetStrategyParameter(parameters, "mySpeed");
        myPath = (LinkedList<Point>) Verifier.verifyAndGetStrategyParameter(parameters, "myPath");
        currentPosition = myPath.poll();
        nextPosition = myPath.poll();
        myDirection = calculateDirection();
    }


    @Override
    public Point getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void updatePosition(double elapsedTime) {
        double diffX = myDirection.getX() * elapsedTime;
        double diffY = myDirection.getY() * elapsedTime;
        double updatedX = currentPosition.getX() + diffX;
        double updatedY = currentPosition.getY() + diffY;
        Point updatedPosition = new Point();
        updatedPosition.setLocation(updatedX, updatedY);
        if(checkDirectionChange(updatedPosition)) {
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
        double updatedX = updatedPosition.getX();
        double updatedY = updatedPosition.getY();
        double nextX = nextPosition.getX();
        double nextY = nextPosition.getY();
        double diffX = Math.abs(updatedX - nextX);
        double diffY = Math.abs(updatedY - nextY);
        double hypotenuse = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        return hypotenuse < DIRECTION_CHANGE_THRESHOLD;
    }

    private void changeDirection() {
        currentPosition = nextPosition;
        if(myPath.peek().equals(myPath.getLast()))
        nextPosition = myPath.poll();
        calculateDirection();
    }
}
