package voogasalad.gameengine.engine.sprites.strategies.movement;

import java.awt.Point;

public class NoMovement implements MovementStrategy {
    @Override
    public Point getCurrentPosition() {
        return null;
    }

    @Override
    public void updatePosition(double elapsedTime) {

    }
}
