package voogasalad.gameengine.playerengineapi.sprites;

import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;

public class JavaFXSprite implements Sprite {
    private int myXCenterCoordinate;
    private int myYCenterCoordinate;
    private final int mySpriteId;
    private HealthStrategy myHealthStrategy;

    public JavaFXSprite(int xPos, int yPos, int spriteId, HealthStrategy healthStrategy) {
        myXCenterCoordinate = xPos;
        myYCenterCoordinate = yPos;
        mySpriteId = spriteId;
    }

    public void printSpriteInfo() {
        System.out.println("SPRITE INFO: x coordinate: " + myXCenterCoordinate + " | y coordinate: " + myYCenterCoordinate + " | id: " + mySpriteId);
    }
}
