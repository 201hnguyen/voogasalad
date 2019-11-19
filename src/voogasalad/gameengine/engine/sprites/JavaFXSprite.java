package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;

import java.awt.*;

public class JavaFXSprite implements Sprite {
    private Point currentPosition;
    private final int mySpriteId;
    private HealthStrategy myHealthStrategy;
    private MovementStrategy myMovementStrategy;

    public JavaFXSprite(double xPos, double yPos, int spriteId, HealthStrategy healthStrategy, MovementStrategy movementStrategy) {
        currentPosition = new Point();
        currentPosition.setLocation(xPos, yPos);
        mySpriteId = spriteId;
        myHealthStrategy = healthStrategy;
        myMovementStrategy = movementStrategy;
    }

    @Override
    public Sprite makeClone(double x, double y, int spriteId) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        return spriteFactory.makeSprite(x, y, spriteId, myHealthStrategy.makeClone(), myMovementStrategy.makeClone());
    }

    @Override
    public double getX() {
        return currentPosition.getX();
    }

    @Override
    public double getY() {
        return currentPosition.getY();
    }

    @Override
    public int getId() {
        return mySpriteId;
    }

    public int getHealth() {
        return myHealthStrategy.getHealth();
    }

    public void updatePosition(double elapsedTime) {
        currentPosition = myMovementStrategy.calculateNextPosition(elapsedTime, currentPosition);
    }
}
