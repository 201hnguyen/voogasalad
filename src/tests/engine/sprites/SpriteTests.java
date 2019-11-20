package tests.engine.sprites;

import org.junit.jupiter.api.Test;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteBuilder;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpriteTests {
    SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
    StrategiesFactory strategiesFactory = new StrategiesFactory();

    @Test
    public void testSpriteMakeClone() throws GameEngineException {
        Map<String, Object> healthParameters = new HashMap<>() {{ put("health", 10); }};
        Map<String, Object> movementParameters = new HashMap<>() {{
            put("path", new LinkedList<>());
            put("speed", 10.0);
        }};
        HealthStrategy healthStrategy = strategiesFactory.makeHealth("Health", healthParameters);
        MovementStrategy movementStrategy = strategiesFactory.makeMovement("PathMovement", movementParameters);

        Sprite prototypeSprite = new SpriteBuilder().setWidth(50).setHeight(50).setImagePath("pandaslogo.png").setHealthStrategy(healthStrategy).setMovementStrategy(movementStrategy).build();

        Sprite clonedSprite = prototypeSprite.makeClone(450, 240, 1);
        assertEquals(450, clonedSprite.getX(), 0.01);
        assertEquals(240, clonedSprite.getY(), 0.01);
        assertEquals(1, clonedSprite.getId());
        assertEquals(10, clonedSprite.getHealth());
    }
}
