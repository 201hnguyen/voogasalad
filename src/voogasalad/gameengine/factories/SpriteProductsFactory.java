package voogasalad.gameengine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.Sprite;
import voogasalad.gameengine.playerengineapi.sprites.SpriteManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class SpriteProductsFactory {
    public static final String SPRITE_FRONTEND_RESOURCE_PATH = "resources.SpriteFrontendSelection";
    private final static String CLASS_PATH = "voogasalad.gameengine.playerengineapi.sprites.";
    private final ResourceBundle SpriteFrontendSelection = ResourceBundle.getBundle(SPRITE_FRONTEND_RESOURCE_PATH);

    public Sprite makeSprite(int xCoordinate, int yCoordinate, int spriteId, SpritePrototypeSpecs spritePrototypeSpecs) throws GameEngineException {
        String spriteClassSelection = SpriteFrontendSelection.getString("Sprite");
        try {
            return (Sprite) Class.forName(CLASS_PATH + spriteClassSelection)
                    .getConstructor(int.class, int.class, int.class, voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs.class)
                    .newInstance(xCoordinate, yCoordinate, spriteId, spritePrototypeSpecs);
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
