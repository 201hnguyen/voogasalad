package voogasalad.gameengine.engine.action;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.elements.LevelMap;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.Sprite;

import java.awt.Point;
import java.util.List;
import java.util.ResourceBundle;

public class SpawnNextWaveAction implements GameAction {

    public static final String MAP_CODES_RESOURCE_PATH = "resources.MapCodes";

    @Override
    public void executeAction(Level level) throws GameEngineException {
        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        ResourceBundle mapCodesBundle = ResourceBundle.getBundle(MAP_CODES_RESOURCE_PATH);
        LevelMap levelMap = level.getLevelMap();
        List<Point> enemySpawnCoordinates = levelMap.getCenterCoordinatesForGroundType(Integer.parseInt(mapCodesBundle.getString("SpawnGround")));

        for (Point spawnCoordinate : enemySpawnCoordinates) {
            System.out.println("Will be spawning enemies at this coordinate:" + spawnCoordinate);
            for (SpritePrototypeSpecs prototypeSpec : level.getSpritePrototypeSpecs()) {
                Sprite sampleSprite = spriteProductsFactory.makeSprite(spawnCoordinate.x, spawnCoordinate.y, level.getNextSpriteId(), prototypeSpec);
                level.getSpriteManager().addSprite(sampleSprite);
            }
        }

    }
}

