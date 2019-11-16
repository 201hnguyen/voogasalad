package voogasalad.gameengine.engine.spritestrategies.movement;

import java.awt.*;

public class NoMovement implements MovementStrategy {
    @Override
    public Point getCurrentPosition() {
        return null;
    }

    @Override
    public void updatePosition(double elapsedTime) {

    }
}
