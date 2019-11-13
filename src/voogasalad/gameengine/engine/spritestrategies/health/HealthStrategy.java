package voogasalad.gameengine.engine.spritestrategies.health;

public interface HealthStrategy {
    Integer getHealth();
    void setHealth(int value);
    void alterHealthByAddition(int value);
}
