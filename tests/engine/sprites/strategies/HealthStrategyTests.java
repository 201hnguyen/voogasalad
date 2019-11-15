package engine.sprites.strategies;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HealthStrategyTests {

    StrategiesFactory strategiesFactory = new StrategiesFactory();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testHealthInitializationValid() throws GameEngineException {
        Integer expectedValue = 10;
        Map<String, Object> parameter = new HashMap<>() {{ put("health", 10); }};
        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
        Assert.assertEquals(expectedValue, health.getHealth());
    }

    @Test
    public void testHealthInitializationInvalidKey() throws GameEngineException {
        exception.expect(GameEngineException.class);
        Map<String, Object> parameter = new HashMap<>() {{ put("heth", 10); }};
        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
    }

    @Test
    public void testHealthInitializationInvalidValue() throws GameEngineException {
        exception.expect(GameEngineException.class);
        Map<String, Object> parameter = new HashMap<>() {{ put("health", new ArrayList<String>()); }};
        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
    }

    @Test
    public void testNoHealthInitializationValid() throws GameEngineException {
        Integer expectedValue = null;
        HealthStrategy health = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
        Assert.assertEquals(expectedValue, health.getHealth());
    }

    @Test
    public void testHealthAlterHealthByAddition() throws GameEngineException {
        Integer expectedValue = 8;
        Map<String, Object> parameter = new HashMap<>() {{ put("health", 10); }};
        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
        health.alterHealthByAddition(5);
        health.alterHealthByAddition(-7);
        Assert.assertEquals(expectedValue, health.getHealth());
    }

    @Test
    public void testNoHealthAlterHealthByAddition() throws GameEngineException {
        Integer expectedValue = null;
        HealthStrategy health = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
        health.alterHealthByAddition(5);
        health.alterHealthByAddition(-7);
        Assert.assertEquals(expectedValue, health.getHealth());
    }

    @Test
    public void testHealthMakeClone() throws GameEngineException {
        Integer expectedValue = 10;
        Map<String, Object> parameter = new HashMap<>() {{ put("health", 10); }};
        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
        HealthStrategy healthClone = health.makeClone();
        Assert.assertEquals(expectedValue, healthClone.getHealth());
    }

    public void testNoHealthMakeClone() throws GameEngineException {
        Integer expectedValue = null;
        HealthStrategy noHealth = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
        HealthStrategy noHealthClone = noHealth.makeClone();
        Assert.assertEquals(expectedValue, noHealthClone.getHealth());
    }
}
