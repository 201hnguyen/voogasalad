package voogasalad.gameengine.engine.spritestrategies.health;

public class NoHealth implements HealthStrategy {

    @Override
    public Integer getHealth() {
        return null;
    }

    @Override
    public void setHealth(int value) {
        // do nothing
    }

    @Override
    public void alterHealthByAddition(int value) {
        // do nothing
    }
}
