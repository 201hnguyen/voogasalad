package voogasalad.gameengine.engine.elements;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.factories.SpriteProductsFactory;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.SpriteManager;

import java.util.Set;

public class Level {
    SpriteManager mySpriteManager;
    LevelMap myLevelMap;
    LevelSpecs myLevelSpecs;
    int mySpriteIdGenerator;
    int myStartTime;

    public Level(LevelSpecs levelSpecs, int startTime) throws GameEngineException {
        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        myLevelSpecs = levelSpecs;
        mySpriteIdGenerator = 0;
        mySpriteManager = spriteProductsFactory.makeSpriteManager();
        myLevelMap = new LevelMap(levelSpecs.getLevelMapSpecs());
        myStartTime = startTime;
    }

    public Set<SpritePrototypeSpecs> getSpritePrototypeSpecs() {
        return myLevelSpecs.getSpritePrototypeSpecs();
    }

    public LevelMap getLevelMap() {
        return myLevelMap;
    }

    public int getNextSpriteId() {
        return mySpriteIdGenerator++;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }
}
