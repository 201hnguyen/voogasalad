package voogasalad.gameengine.playerengineapi.sprites;

import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;

public class JavaFXSprite implements Sprite {
    private int myXCenterCoordinate;
    private int myYCenterCoordinate;
    private final int mySpriteId;
    private final SpritePrototypeSpecs myPrototypeSpecs;

    public JavaFXSprite(int xPos, int yPos, int spriteId, SpritePrototypeSpecs spritePrototypeSpecs) {
        myPrototypeSpecs = spritePrototypeSpecs;
        myXCenterCoordinate = xPos;
        myYCenterCoordinate = yPos;
        mySpriteId = spriteId;
    }

    public void printSpriteInfo() {
        System.out.println("SPRITE INFO: x coordinate: " + myXCenterCoordinate + " | y coordinate: " + myYCenterCoordinate + " | id: " + mySpriteId + " | prototype id: " + myPrototypeSpecs.getPrototypeId());
    }
}
