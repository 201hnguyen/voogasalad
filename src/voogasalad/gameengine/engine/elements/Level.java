package voogasalad.gameengine.engine.elements;

import voogasalad.gameengine.engine.conditions.GameCondition;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;
import voogasalad.gameengine.playerengineapi.sprites.SpriteManager;

import java.util.List;
import java.util.Set;

public class Level {
    private final SpriteManager mySpriteManager;
    private LevelMap myLevelMap;
    private LevelSpecs myLevelSpecs;
    private int mySpriteIdGenerator;
    private int myElapsedTime;
    private List<GameCondition> myLevelGameConditions;

    public Level(LevelSpecs levelSpecs) throws GameEngineException {
        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        myLevelSpecs = levelSpecs;
        mySpriteIdGenerator = 0;
        mySpriteManager = spriteProductsFactory.makeSpriteManager();
        myLevelMap = new LevelMap(levelSpecs.getLevelMapSpecs());
        myElapsedTime = 0;
        myLevelGameConditions = levelSpecs.getLevelConditions();
    }

    public int getElapsedTime() {
        return myElapsedTime;
    }

    public void executeNextScene() throws GameEngineException {
        for (GameCondition gameCondition: myLevelGameConditions) {
            if (gameCondition.conditionIsMet(this)) {
                gameCondition.executeAction(this);
            }
        }
        myElapsedTime++;
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
