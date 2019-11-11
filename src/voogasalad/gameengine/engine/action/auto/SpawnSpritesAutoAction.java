package voogasalad.gameengine.engine.action.auto;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.elements.LevelMap;
import voogasalad.gameengine.factories.SpriteProductsFactory;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.Sprite;

import java.awt.Point;
import java.util.List;
import java.util.ResourceBundle;

public class SpawnSpritesAutoAction implements GameAutoAction {

    public static final String MAP_CODES_RESOURCE_PATH = "resources.MapCodes";

    @Override
    public void executeAction(Level level) {
        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        ResourceBundle mapCodesBundle = ResourceBundle.getBundle(MAP_CODES_RESOURCE_PATH);
        LevelMap levelMap = level.getLevelMap();
        List<Point> enemySpawnCoordinates = levelMap.getCenterCoordinatesForGroundType(Integer.parseInt(mapCodesBundle.getString("SpawnGround")));

        for (Point spawnCoordinate : enemySpawnCoordinates) {
            System.out.println("Will be spawning enemies at this coordinate:" + spawnCoordinate);
            //TODO: This should be spawning waves instead of sprites, and shouldn't be calling new; also use factories or some shit to construct JavaFXSprite so we're not explicitly calling it
            for (SpritePrototypeSpecs prototypeSpec : level.getSpritePrototypeSpecs()) {
                Sprite sampleSprite = spriteProductsFactory.makeSprite(spawnCoordinate.x, spawnCoordinate.y, level.getNextSpriteId(), prototypeSpec);
                level.getSpriteManager().addSprite(sampleSprite);
            }
        }

    }
}

