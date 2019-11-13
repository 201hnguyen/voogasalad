package voogasalad.gameengine.engine.spritestrategies.health;

public class Health implements HealthStrategy {

    private Integer myHealth;

    public void setHealth(int value) {
        myHealth = value;
        System.out.println("Health value set to: " + myHealth);
    }

    @Override
    public Integer getHealth() {
        return myHealth;
    }

    @Override
    public void alterHealthByAddition(int value) {
        myHealth+=value;
    }
}
