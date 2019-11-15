package voogasalad.gameengine.engine.spritestrategies.health;

public interface HealthStrategy {
    Integer getHealth();
    void alterHealthByAddition(int value);
}
