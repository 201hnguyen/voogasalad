//package tests.engine.strategies;
//
//import org.junit.jupiter.api.Test;
//import voogasalad.gameengine.engine.exceptions.GameEngineException;
//import voogasalad.gameengine.engine.factories.StrategiesFactory;
//import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class HealthStrategyTests {
//
//    StrategiesFactory strategiesFactory = new StrategiesFactory();
//
//    @Test
//    public void testHealthInitializationValid() throws GameEngineException {
//        Integer expectedValue = 10;
//        Map<String, Object> parameter = new HashMap<>() {{ put("health", 10); }};
//        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
//        assertEquals(expectedValue, health.getHealth());
//    }
//
//    @Test
//    public void testHealthInitializationInvalidKey(){
//        Map<String, Object> parameter = new HashMap<>() {{ put("heth", 10); }};
//        assertThrows(GameEngineException.class, () -> { HealthStrategy health = strategiesFactory.makeHealth("Health", parameter); });
//    }
//
//    @Test
//    public void testHealthInitializationInvalidValue() throws GameEngineException {
//        Map<String, Object> parameter = new HashMap<>() {{ put("health", new ArrayList<String>()); }};
//        assertThrows(GameEngineException.class, () -> { HealthStrategy health = strategiesFactory.makeHealth("Health", parameter); });
//    }
//
//    @Test
//    public void testNoHealthInitializationValid() throws GameEngineException {
//        Integer expectedValue = null;
//        HealthStrategy health = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
//        assertEquals(expectedValue, health.getHealth());
//    }
//
//    @Test
//    public void testHealthAlterHealthByAddition() throws GameEngineException {
//        Integer expectedValue = 8;
//        Map<String, Object> parameter = new HashMap<>() {{ put("health", 10); }};
//        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
//        health.alterHealthByAddition(5);
//        health.alterHealthByAddition(-7);
//        assertEquals(expectedValue, health.getHealth());
//    }
//
//    @Test
//    public void testNoHealthAlterHealthByAddition() throws GameEngineException {
//        Integer expectedValue = null;
//        HealthStrategy health = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
//        health.alterHealthByAddition(5);
//        health.alterHealthByAddition(-7);
//        assertEquals(expectedValue, health.getHealth());
//    }
//
//    @Test
//    public void testHealthMakeClone() throws GameEngineException {
//        Integer expectedValue = 10;
//        Map<String, Object> parameter = new HashMap<>() {{ put("health", 10); }};
//        HealthStrategy health = strategiesFactory.makeHealth("Health", parameter);
//        HealthStrategy healthClone = health.makeClone();
//        assertEquals(expectedValue, healthClone.getHealth());
//    }
//
//    @Test
//    public void testNoHealthMakeClone() throws GameEngineException {
//        Integer expectedValue = null;
//        HealthStrategy noHealth = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
//        HealthStrategy noHealthClone = noHealth.makeClone();
//        assertEquals(expectedValue, noHealthClone.getHealth());
//    }
//}
