package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy;
import voogasalad.gameengine.playerengineapi.sprites.Sprite;
import voogasalad.gameengine.playerengineapi.sprites.SpriteManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class SpriteProductsFactory {
    public static final String SPRITE_FRONTEND_RESOURCE_PATH = "resources.engine.SpriteFrontendSelection";
    private final static String CLASS_PATH = "voogasalad.gameengine.playerengineapi.sprites.";
    private final ResourceBundle SpriteFrontendSelection = ResourceBundle.getBundle(SPRITE_FRONTEND_RESOURCE_PATH);

    public Sprite makeSprite(int xCoordinate, int yCoordinate, int spriteId, HealthStrategy healthStrategy) throws GameEngineException {
        String spriteClassSelection = SpriteFrontendSelection.getString("Sprite");
        try {
            return (Sprite) Class.forName(CLASS_PATH + spriteClassSelection)
                    .getConstructor(int.class, int.class, int.class, voogasalad.gameengine.engine.spritestrategies.health.HealthStrategy.class)
                    .newInstance(xCoordinate, yCoordinate, spriteId, healthStrategy);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new GameEngineException(e, "SpriteProductionFailed");
        }
    }

    public SpriteManager makeSpriteManager() throws GameEngineException {
        String spriteManagerClassSelection = SpriteFrontendSelection.getString("SpriteManager");
        try {
            return (SpriteManager) Class.forName(CLASS_PATH + spriteManagerClassSelection).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new GameEngineException(e, "SpriteManagerProductionFailed");
        }
    }
}
