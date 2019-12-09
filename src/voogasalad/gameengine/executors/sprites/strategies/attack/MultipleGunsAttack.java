package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.AttackBuilder;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MultipleGunsAttack implements AttackStrategy {

    private AttackBuilder myBuilder;
    private double attackRate; //how many ticks until the next shot?
    private double elapsedTimeSinceLastAttack;
    private Integer bulletPrototypeID;
    private ArrayList<Double> shootingPositions;

    public MultipleGunsAttack(AttackBuilder attackBuilder){
        myBuilder = attackBuilder;
        attackRate = myBuilder.getAttackRate();
        bulletPrototypeID = myBuilder.getBulletPrototypeId();
        shootingPositions = myBuilder.getMyShootingPositions();
    }

    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos) throws GameEngineException {
        elapsedTimeSinceLastAttack += elapsedTime;
        if(elapsedTimeSinceLastAttack >= attackRate){
            elapsedTimeSinceLastAttack = 0;
            shootBullet(currentAngle, actionsRequester, currentPos);
        }
    }

    private void shootBullet(double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos){
        for(double gun : shootingPositions){
            double shootFrom = (gun + currentAngle) % 360;
            LevelAction action = new AddSpriteAction(bulletPrototypeID, currentPos.getX(), currentPos.getY(), Math.toRadians(shootFrom));
            actionsRequester.requestAction(action);
            System.out.println("Shot at angle " + shootFrom);
        }
    }

    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        return new MultipleGunsAttack(myBuilder);
    }
}
