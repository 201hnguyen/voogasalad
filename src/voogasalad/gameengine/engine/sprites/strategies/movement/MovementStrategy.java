package voogasalad.gameengine.engine.sprites.strategies.movement;

import java.awt.Point;

public interface MovementStrategy {

    Point getCurrentPosition();

    void updatePosition(double elapsedTime);
}
