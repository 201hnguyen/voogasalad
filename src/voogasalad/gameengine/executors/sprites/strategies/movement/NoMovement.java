package voogasalad.gameengine.executors.sprites.strategies.movement;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class NoMovement implements MovementStrategy {

    public NoMovement(Map<String, Object> parameters) {
    }

    @Override
    public MovementStrategy makeClone() {
        return new NoMovement(new HashMap<>());
    }

    @Override
    public Point calculateNextPosition(double elapsedTime, Point currentPosition) {
        return currentPosition;
    }
}
