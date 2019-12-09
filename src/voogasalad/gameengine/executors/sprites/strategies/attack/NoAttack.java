package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.AttackBuilder;

import java.awt.geom.Point2D;

public class NoAttack implements AttackStrategy {

    public NoAttack(AttackBuilder attackBuilder){ }

    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos) {
        //will never attack
    }

    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        return new NoAttack(new AttackBuilder());
    }
}
