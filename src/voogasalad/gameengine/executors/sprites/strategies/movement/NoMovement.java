package voogasalad.gameengine.executors.sprites.strategies.movement;

import java.awt.geom.Point2D;
import java.util.List;

public class NoMovement implements MovementStrategy {

    public NoMovement() {
    }

    @Override
    public MovementStrategy makeClone() {
        return new NoMovement();
    }

    @Override
    public Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition) {
        return currentPosition;
    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        //do nothing
    }

    @Override
    public void updateDirectionalAngle(double angle) {

    }

    @Override
    public boolean isMovementFinished() {
        return false;
    }
}
