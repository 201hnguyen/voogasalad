package voogasalad;

import voogasalad.gameengine.engine.GameEngine;
import voogasalad.gameengine.playerengineapi.specs.LevelMapSpecs;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;

import java.util.HashSet;
import java.util.Set;

public class MockPlayer {

    public MockPlayer() {
        GameEngine engine = new GameEngine();
        int[] myMapEncodings = new int[] {0, 0, 0, 2, 1, 1, 0, 0, 0};
        LevelMapSpecs levelMapSpecs = new LevelMapSpecs(3, 3, myMapEncodings, 900, 900);
        SpritePrototypeSpecs spritePrototypeSpecs = new SpritePrototypeSpecs(10, 1);
        SpritePrototypeSpecs spritePrototypeSpecs2 = new SpritePrototypeSpecs(15, 2);
        Set<SpritePrototypeSpecs> spritePrototypes = new HashSet<>() {{ add(spritePrototypeSpecs); add(spritePrototypeSpecs2); }} ;
        LevelSpecs levelSpecs = new LevelSpecs(levelMapSpecs, spritePrototypes);
        engine.createNewLevel(levelSpecs);

        int maxTime = 5;
        for (int i=0; i<maxTime; i++) {
            System.out.println("CURRENT TIME: " + i);
            engine.executeNextScene(i);
        }
    }
}
