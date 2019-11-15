package voogasalad.gameengine.engine.spritestrategies.health;

import java.util.Map;

public class NoHealth implements HealthStrategy {

    public NoHealth(Map<String, Object> parameters) {}

    @Override
    public Integer getHealth() {
        return null;
    }

    @Override
    public void alterHealthByAddition(int value) {
        // do nothing
    }
}
