package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.awt.geom.Point2D;

public interface AttackStrategy {
    void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos)
            throws GameEngineException;

    Double getAttackRate();

    AttackStrategy makeClone() throws GameEngineException;
}