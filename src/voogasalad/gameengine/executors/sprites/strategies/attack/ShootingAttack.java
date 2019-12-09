package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.AttackBuilder;

import java.awt.geom.Point2D;

public class ShootingAttack implements AttackStrategy {

    private AttackBuilder myBuilder;
    private double attackRate; //how many ticks until the next shot?
    private double elapsedTimeSinceLastAttack;
    private Integer bulletPrototypeID;


    public ShootingAttack(AttackBuilder attackBuilder) {
        myBuilder = attackBuilder;
        attackRate = myBuilder.getAttackRate();
        bulletPrototypeID = myBuilder.getBulletPrototypeId();
    }

    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos) {
        elapsedTimeSinceLastAttack += elapsedTime;
        if(elapsedTimeSinceLastAttack >= attackRate){
            elapsedTimeSinceLastAttack = 0;
            shootBullet(currentAngle, actionsRequester, currentPos);
        }
    }

    private void shootBullet(double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos){
        LevelAction action = new AddSpriteAction(bulletPrototypeID, currentPos.getX() + Math.cos(currentAngle), currentPos.getY() + Math.sin(currentAngle), Math.toRadians(currentAngle));
        actionsRequester.requestAction(action);
    }

    @Override
    public Double getAttackRate() {
        return attackRate;
    }

    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        return new ShootingAttack(myBuilder);
    }
}