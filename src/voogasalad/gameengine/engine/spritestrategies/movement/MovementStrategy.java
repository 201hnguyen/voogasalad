package voogasalad.gameengine.engine.spritestrategies.movement;

import java.awt.Point;

public interface MovementStrategy {

    Point getCurrentPosition();

    void updatePosition(double elapsedTime);
}
