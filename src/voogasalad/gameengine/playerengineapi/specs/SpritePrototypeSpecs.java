package voogasalad.gameengine.playerengineapi.specs;

public class SpritePrototypeSpecs {
    private final int myPrototypeId;

    public SpritePrototypeSpecs(int prototypeId) {
        myPrototypeId = prototypeId;
    }

    public int getPrototypeId() {
        return myPrototypeId;
    }
}
