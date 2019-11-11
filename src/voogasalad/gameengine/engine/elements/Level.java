package voogasalad.gameengine.engine.elements;

import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.JavaFXSpriteManager;

import java.util.Set;

public class Level {
    JavaFXSpriteManager myJavaFXSpriteManager;
    LevelMap myLevelMap;
    LevelSpecs myLevelSpecs;
    int mySpriteIdGenerator;

    public Level(LevelSpecs levelSpecs) {
        myLevelSpecs = levelSpecs;
        mySpriteIdGenerator = 0;
        myJavaFXSpriteManager = new JavaFXSpriteManager();
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

    public JavaFXSpriteManager getSpriteManager() {
        return myJavaFXSpriteManager;
    }

}
