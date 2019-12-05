package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.strategies.movement.PathMovement;
import voogasalad.gameengine.executors.utils.Verifier;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;

public class ShootingAttack implements AttackStrategy {

    private Map<String, Object> originalParameters;
    private Double attackRate; //shots per minute
    private Map<String, Object> bulletPathParameters;
    private Double bulletSpeed;
    private Double range;
    private LinkedList<Point> bulletPath;

    ShootingAttack(Map<String, Object> parameters) throws GameEngineException{
        originalParameters = parameters;
        attackRate = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myAttackRate");
        bulletSpeed = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myBulletSpeed");
        range = (Double) Verifier.verifyAndGetStrategyParameter(parameters, "myAttackRange");
    }

    @Override
    public void attack(double elapsedTime) throws GameEngineException {
        if(elapsedTime % 2 != 0){  //TODO: make this variable with rate
            constructBulletPathParameters();
            SpriteBuilder spriteBuilder = new SpriteBuilder();
            spriteBuilder.setPrototypeId("bullet");
            spriteBuilder.setMovementStrategy(new PathMovement(bulletPathParameters));
            Sprite bullet = spriteBuilder.build();
            //TODO: spawn this bullet
        }
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

    private void buildPath(){
        //TODO: start should be based on rotation
        //TODO: end should be a straight line from start, at the end of range
    }

    private void constructBulletPathParameters(){
        buildPath();
        bulletPathParameters.put("mySpeed", bulletSpeed); //TODO: make sure original parameters map has everything
        bulletPathParameters.put("myPath", bulletPath);
    }
}
