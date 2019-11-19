package voogasalad.gameengine.engine.sprites.strategies.movement;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

import java.awt.Point;

public interface MovementStrategy {

    MovementStrategy makeClone() throws GameEngineException;

    Point calculateNextPosition(double elapsedTime, Point currentPosition);
}
