package engine.sprites;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

import java.util.HashMap;
import java.util.Map;

public class SpriteTests {
    SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
    StrategiesFactory strategiesFactory = new StrategiesFactory();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSpriteMakeClone() throws GameEngineException {
        Map<String, Object> healthParameters = new HashMap<>() {{ put("health", 10); }};
        HealthStrategy healthStrategy = strategiesFactory.makeHealth("Health", healthParameters);
        Sprite prototypeSprite = spriteProductsFactory.makeSprite(0, 0, 0, healthStrategy);
        Sprite clonedSprite = prototypeSprite.makeClone(450, 240, 1);
        Assert.assertEquals(0, prototypeSprite.getX());
        Assert.assertEquals(0, prototypeSprite.getY());
        Assert.assertEquals(0, prototypeSprite.getId());
        Assert.assertEquals(10, prototypeSprite.getHealth());
        Assert.assertEquals(450, clonedSprite.getX());
        Assert.assertEquals(240, clonedSprite.getY());
        Assert.assertEquals(1, clonedSprite.getId());
        Assert.assertEquals(10, clonedSprite.getHealth());
    }
}
