//package tests.engine.sprites;
//
//import org.junit.jupiter.api.Test;
//import voogasalad.gameengine.engine.exceptions.GameEngineException;
//import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
//import voogasalad.gameengine.engine.factories.StrategiesFactory;
//import voogasalad.gameengine.engine.sprites.Sprite;
//import voogasalad.gameengine.engine.sprites.SpriteManager;
//import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class SpriteManagerTests {
//    SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
//    StrategiesFactory strategiesFactory = new StrategiesFactory();
//
//
//    @Test
//    public void testMakeSpriteFromPrototype() throws GameEngineException {
//        SpriteManager spriteManager = spriteProductsFactory.makeSpriteManager();
//        Map<String, Object> healthParameters1 = new HashMap<>() {{ put("health", 10); }};
//        Map<String, Object> healthParameters2 = new HashMap<>() {{ put("health", 15); }};
//        HealthStrategy healthStrategy1 = strategiesFactory.makeHealth("Health", healthParameters1);
//        HealthStrategy healthStrategy2 = strategiesFactory.makeHealth("Health", healthParameters2);
//        Sprite spritePrototype1 = spriteProductsFactory.makeSprite(0, 0, 0, healthStrategy1);
//        Sprite spritePrototype2 = spriteProductsFactory.makeSprite(0, 0, 0, healthStrategy2);
//        spriteManager.addSpritePrototype(5, spritePrototype1);
//        spriteManager.addSpritePrototype(6, spritePrototype2);
//        spriteManager.makeSpriteFromPrototype(250, 100, 5);
//        spriteManager.makeSpriteFromPrototype(100, 200, 6);
//        assertEquals(10, spriteManager.getOnScreenSprites().get(0).getHealth());
//        assertEquals(15, spriteManager.getOnScreenSprites().get(1).getHealth());
//        assertEquals(0, spriteManager.getOnScreenSprites().get(0).getId());
//        assertEquals(1, spriteManager.getOnScreenSprites().get(1).getId());
//    }
//}
