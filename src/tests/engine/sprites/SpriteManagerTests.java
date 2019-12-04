package tests.engine.sprites;

import org.junit.jupiter.api.Test;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.objectcreators.SpriteProductsFactory;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
import voogasalad.gameengine.executors.sprites.SpriteManager;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpriteManagerTests {
    SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
    StrategiesFactory strategiesFactory = new StrategiesFactory();


    @Test
    public void testMakeSpriteFromPrototype() throws GameEngineException {
        LevelActionsRequester levelActionsRequester  = new LevelActionsRequester();
        SpriteManager spriteManager = spriteProductsFactory.makeSpriteManager(levelActionsRequester);
        Map<String, Object> healthParameters1 = new HashMap<>() {{ put("health", 10); }};
        Map<String, Object> healthParameters2 = new HashMap<>() {{ put("health", 15); }};
        Map<String, Object> movementParameters = new HashMap<>() {{
            put("path", new LinkedList<>());
            put("speed", 10.0);
        }};
        HealthStrategy healthStrategy1 = strategiesFactory.makeHealth("Health", healthParameters1);
        HealthStrategy healthStrategy2 = strategiesFactory.makeHealth("Health", healthParameters2);
        MovementStrategy movementStrategy = strategiesFactory.makeMovement("PathMovement", movementParameters);

        Sprite spritePrototype1 = new SpriteBuilder().setWidth(50).setHeight(50).setImagePath("pandaslogo.png").setHealthStrategy(healthStrategy1).setMovementStrategy(movementStrategy).build();
        Sprite spritePrototype2 = new SpriteBuilder().setWidth(50).setHeight(50).setImagePath("pandaslogo.png").setHealthStrategy(healthStrategy2).setMovementStrategy(movementStrategy).build();

        spriteManager.addSpritePrototype(spritePrototype1);
        spriteManager.addSpritePrototype(spritePrototype2);
        spriteManager.makeSpriteFromPrototype(250, 100, 5);
        spriteManager.makeSpriteFromPrototype(100, 200, 6);
        assertEquals(10, spriteManager.getOnScreenSprites().get(0).getHealth());
        assertEquals(15, spriteManager.getOnScreenSprites().get(1).getHealth());
        assertEquals(0, spriteManager.getOnScreenSprites().get(0).getId());
        assertEquals(1, spriteManager.getOnScreenSprites().get(1).getId());
    }
}
