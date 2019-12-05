package tests.engine.strategies;

import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AttackStrategyTests{
    private StrategiesFactory factory = new StrategiesFactory();
    AttackStrategy ShootingAttack;
    AttackStrategy NoAttack;

    @BeforeEach
    public void setUp() throws GameEngineException{
        Map<String, Object> shootingParameters = new HashMap<>();
        shootingParameters.put("attack_rate", 5.0);
        shootingParameters.put("attack_range", 40.0);
        shootingParameters.put("bullet_speed", 30.0);

        ShootingAttack = factory.makeAttack("ShootingAttack", shootingParameters);
        NoAttack = factory.makeAttack("NoAttack", shootingParameters);
    }

    @Test
    public void testShootingGetAttackRate(){
        Double expected = 5.0;
        assertEquals(expected, ShootingAttack.getAttackRate());
    }

    @Test
    public void testNoneGetAttackRate(){
        Double expected = 0.0;
        assertEquals(expected, NoAttack.getAttackRate());
    }

}
