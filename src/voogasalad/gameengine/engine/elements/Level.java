package voogasalad.gameengine.engine.elements;

import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.playerengineapi.sprites.SpriteManager;

import java.util.Set;

public class Level {
    SpriteManager mySpriteManager;
    LevelMap myLevelMap;
    LevelSpecs myLevelSpecs;
    int mySpriteIdGenerator;

    public Level(LevelSpecs levelSpecs) {
        myLevelSpecs = levelSpecs;
        mySpriteIdGenerator = 0;
        //TODO: This shouldn't be created using new and calling explicitly to JavaFXSpriteManager
        mySpriteManager = new JavaFXSpriteManager();
        myLevelMap = new LevelMap(levelSpecs.getLevelMapSpecs());
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
