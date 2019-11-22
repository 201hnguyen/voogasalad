package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.awt.Point;

public interface MovementStrategy {

    MovementStrategy makeClone() throws GameEngineException;

    Point calculateNextPosition(double elapsedTime, Point currentPosition);
}
