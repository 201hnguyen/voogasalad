package voogasalad.gameengine.playerengineapi.sprites;

import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;

public class JavaFXSprite implements Sprite {
    private int myXCenterCoordinate;
    private int myYCenterCoordinate;
    private int myHealth;
    private int mySpriteId;
    private SpritePrototypeSpecs myPrototypeSpecs;

    public JavaFXSprite(int xPos, int yPos, int spriteId, SpritePrototypeSpecs spritePrototypeSpecs) {
        myPrototypeSpecs = spritePrototypeSpecs;
        myXCenterCoordinate = xPos;
        myYCenterCoordinate = yPos;
        myHealth = spritePrototypeSpecs.getHealth();
        mySpriteId = spriteId;
    }

    public void printSpriteInfo() {
        System.out.println("SPRITE INFO: x coordinate: " + myXCenterCoordinate + " | y coordinate: " + myYCenterCoordinate + " | health: " + myHealth + " | id: " + mySpriteId + " | prototype id: " + myPrototypeSpecs.getPrototypeId());
    }
}
