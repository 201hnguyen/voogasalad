package voogasalad.gameengine.playerengineapi.specs;

import java.util.Set;

public class LevelSpecs {
    private final LevelMapSpecs myLevelMapSpecs;
    private final Set<SpritePrototypeSpecs> mySpritePrototypeSpecs;

    public LevelSpecs(LevelMapSpecs levelMapSpecs, Set<SpritePrototypeSpecs> spritePrototypeSpecs) {
        mySpritePrototypeSpecs = spritePrototypeSpecs;
        myLevelMapSpecs = levelMapSpecs;
    }

    public LevelMapSpecs getLevelMapSpecs() {
        return myLevelMapSpecs;
    }

    public Set<SpritePrototypeSpecs> getSpritePrototypeSpecs() {
        return mySpritePrototypeSpecs;
    }
}
