package voogasalad;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;
import voogasalad.gameengine.playerengineapi.sprites.Sprite;
import voogasalad.gameengine.playerengineapi.sprites.SpriteManager;

import java.util.*;

public class MockPlayer {

    public MockPlayer() throws GameEngineException {
        int[] myMapEncodings = new int[] {0, 0, 0, 2, 1, 1, 0, 0, 0};


        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        StrategiesFactory strategiesFactory = new StrategiesFactory();
        SpriteManager spriteManager = spriteProductsFactory.makeSpriteManager();
        HealthStrategy healthStrategy = strategiesFactory.makeHealth("Health");
        Sprite sprite = spriteProductsFactory.makeSprite(450, 450, 0, healthStrategy);

    }
}
