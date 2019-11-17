package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

public class JavaFXSprite implements Sprite {
    private double myXCenterCoordinate;
    private double myYCenterCoordinate;
    private final int mySpriteId;
    private HealthStrategy myHealthStrategy;

    public JavaFXSprite(double xPos, double yPos, int spriteId, HealthStrategy healthStrategy) {
        myXCenterCoordinate = xPos;
        myYCenterCoordinate = yPos;
        mySpriteId = spriteId;
        myHealthStrategy = healthStrategy;
    }

    @Override
    public Sprite makeClone(double x, double y, int spriteId) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        return spriteFactory.makeSprite(x, y, spriteId, myHealthStrategy.makeClone());
    }

    @Override
    public double getX() {
        return myXCenterCoordinate;
    }

    @Override
    public double getY() {
        return myYCenterCoordinate;
    }

    @Override
    public int getId() {
        return mySpriteId;
    }

    public int getHealth() {
        return myHealthStrategy.getHealth();
    }
}
