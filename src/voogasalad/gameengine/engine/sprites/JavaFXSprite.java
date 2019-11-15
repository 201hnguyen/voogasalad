package voogasalad.gameengine.engine.sprites;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

public class JavaFXSprite implements Sprite {
    private int myXCenterCoordinate;
    private int myYCenterCoordinate;
    private final int mySpriteId;
    private HealthStrategy myHealthStrategy;

    public JavaFXSprite(int xPos, int yPos, int spriteId, HealthStrategy healthStrategy) {
        myXCenterCoordinate = xPos;
        myYCenterCoordinate = yPos;
        mySpriteId = spriteId;
        myHealthStrategy = healthStrategy;
    }

    @Override
    public Sprite makeClone(int x, int y, int spriteId) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        return spriteFactory.makeSprite(x, y, spriteId, myHealthStrategy.makeClone());
    }

    @Override
    public int getX() {
        return myXCenterCoordinate;
    }

    @Override
    public int getY() {
        return myYCenterCoordinate;
    }

    @Override
    public int getId() {
        return mySpriteId;
    }

    public HealthStrategy getHealthStrategy() throws GameEngineException {
        return myHealthStrategy.makeClone();
    }
}
