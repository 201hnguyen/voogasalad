package voogasalad.gameengine.playerengineapi.specs;

public class SpritePrototypeSpecs {
    private final int myHealth;
    private final int myPrototypeId;

    public SpritePrototypeSpecs(int health, int prototypeId) {
        myHealth = health;
        myPrototypeId = prototypeId;
    }

    public int getHealth() {
        return myHealth;
    }

    public int getPrototypeId() {
        return myPrototypeId;
    }
}
