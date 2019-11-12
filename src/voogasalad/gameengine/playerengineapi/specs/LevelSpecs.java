package voogasalad.gameengine.playerengineapi.specs;

import voogasalad.gameengine.engine.conditions.GameCondition;

import java.util.List;
import java.util.Set;

public class LevelSpecs {
    private final LevelMapSpecs myLevelMapSpecs;
    private final Set<SpritePrototypeSpecs> mySpritePrototypeSpecs;
    private final List<GameCondition> myConditions;

    public LevelSpecs(LevelMapSpecs levelMapSpecs, Set<SpritePrototypeSpecs> spritePrototypeSpecs, List<GameCondition> levelConditions) {
        mySpritePrototypeSpecs = spritePrototypeSpecs;
        myLevelMapSpecs = levelMapSpecs;
        myConditions = levelConditions;
    }

    public LevelMapSpecs getLevelMapSpecs() {
        return myLevelMapSpecs;
    }

    public Set<SpritePrototypeSpecs> getSpritePrototypeSpecs() {
        return mySpritePrototypeSpecs;
    }

    public List<GameCondition> getLevelConditions() {
        return myConditions;
    }
}
