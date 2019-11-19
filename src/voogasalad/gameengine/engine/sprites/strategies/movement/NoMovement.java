package voogasalad.gameengine.engine.sprites.strategies.movement;

import java.awt.Point;

public class NoMovement implements MovementStrategy {

    @Override
    public Point calculateNextPosition(double elapsedTime, Point currentPosition) {
        return currentPosition;
    }
}
