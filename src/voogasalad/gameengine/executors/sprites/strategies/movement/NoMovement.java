package voogasalad.gameengine.executors.sprites.strategies.movement;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

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
}
