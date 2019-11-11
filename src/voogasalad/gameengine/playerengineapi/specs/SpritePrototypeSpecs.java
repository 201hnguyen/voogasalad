package voogasalad.gameengine.playerengineapi.specs;

public class SpritePrototypeSpecs {
    private int myHealth;
    private int myPrototypeId;

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
