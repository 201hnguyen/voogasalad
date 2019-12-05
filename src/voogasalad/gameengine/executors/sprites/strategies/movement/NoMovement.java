package voogasalad.gameengine.executors.sprites.strategies.movement;

import java.awt.Point;
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
    public Point calculateNextPosition(double elapsedTime, Point currentPosition) {
        return currentPosition;
    }
}
