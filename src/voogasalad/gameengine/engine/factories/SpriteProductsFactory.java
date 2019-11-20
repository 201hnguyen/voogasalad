package voogasalad.gameengine.engine.factories;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.sprites.SpriteBuilder;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteManager;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class SpriteProductsFactory {
    public static final String SPRITE_FRONTEND_RESOURCE_PATH = "resources.engine.SpriteFrontendSelection";
    private final static String CLASS_PATH = "voogasalad.gameengine.engine.sprites.";
    private final ResourceBundle SpriteFrontendSelection = ResourceBundle.getBundle(SPRITE_FRONTEND_RESOURCE_PATH);

    public SpriteManager makeSpriteManager() throws GameEngineException {
        String spriteManagerClassSelection = SpriteFrontendSelection.getString("SpriteManager");
        try {
            return (SpriteManager) Class.forName(CLASS_PATH + spriteManagerClassSelection).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace(); //TODO: Delete; currently here so we can see what is going on.
            throw new GameEngineException(e, "SpriteManagerProductionFailed");
        }
    }

    public Sprite makeSprite(SpriteBuilder builder) throws GameEngineException {
        String spriteClassSelection = SpriteFrontendSelection.getString("Sprite");
        try {
            return (Sprite) Class.forName(CLASS_PATH + spriteClassSelection)
                    .getConstructor(SpriteBuilder.class)
                    .newInstance(builder);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace(); //TODO: Delete; currently here so we can see what is going on.
            throw new GameEngineException(e, "SpriteProductionFailed");
        }
    }
}
