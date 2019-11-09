package usecases;

// Designer should be able to set the speed of the enemy.

public class EnemyUseCase {

    //access the section of the GAE for designing enemies
    Object myDisplay = getDisplay();

    //access the field within this section for speed and set speed
    int myEnemySpeed = getEnemySpeed();

    //A display object
    private Object getDisplay() {
        Object disp = new Object();
        return disp;
    }

    //The enemy speed field
    private int getEnemySpeed() {
        int speed = 0;
        return speed;
    }

}