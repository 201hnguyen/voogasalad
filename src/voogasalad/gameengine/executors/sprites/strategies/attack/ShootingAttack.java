package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.strategies.movement.PathMovement;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;
import voogasalad.gameengine.executors.utils.Verifier;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Map;

public class ShootingAttack implements AttackStrategy {

    private Map<String, Object> originalParameters;
    private Double attackRate; //how many ticks until the next shot?
    private Map<String, Object> bulletPathParameters;
    private Double bulletSpeed;
    private double elapsedTimeSinceLastAttack; //TODO: make double/Double consistent
    private int bulletPrototypeID;


    public ShootingAttack(Map<String, Object> parameters) throws GameEngineException{
        originalParameters = parameters;
        attackRate = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myAttackRate");
        bulletSpeed = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "mySpeed");
    }

    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos)
            throws GameEngineException {
        elapsedTimeSinceLastAttack += elapsedTime;
        if(elapsedTimeSinceLastAttack >= attackRate){
            shootBullet(actionsRequester, currentPos);
            //TODO: spawn this bullet
        }
    }

    private void shootBullet(LevelActionsRequester actionsRequester, Point2D.Double currentPos){
        LevelAction action = new AddSpriteAction(bulletPrototypeID, currentPos.getX(), currentPos.getY());
        actionsRequester.requestAction(action);
    }

    @Override
    public Double getAttackRate() {
        return attackRate;
    }

    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        StrategiesFactory factory = new StrategiesFactory();
        return factory.makeAttack("ShootingAttack", originalParameters);
    }
}