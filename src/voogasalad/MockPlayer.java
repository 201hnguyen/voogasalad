package voogasalad;

import voogasalad.gameengine.engine.GameEngine;
import voogasalad.gameengine.engine.conditions.GameCondition;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.playerengineapi.specs.LevelMapSpecs;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;
import voogasalad.gameengine.playerengineapi.specs.SpritePrototypeSpecs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockPlayer {

    public MockPlayer() {
        int[] myMapEncodings = new int[] {0, 0, 0, 2, 1, 1, 0, 0, 0};

        LevelMapSpecs levelMapSpecs = new LevelMapSpecs(3, 3, myMapEncodings, 900, 900);
        SpritePrototypeSpecs spritePrototypeSpecs1 = new SpritePrototypeSpecs(10, 1);
        SpritePrototypeSpecs spritePrototypeSpecs2 = new SpritePrototypeSpecs(15, 2);
        Set<SpritePrototypeSpecs> spritePrototypes = new HashSet<>() {{ add(spritePrototypeSpecs1); add(spritePrototypeSpecs2); }} ;
        LevelSpecs levelSpecs = new LevelSpecs(levelMapSpecs, spritePrototypes, new ArrayList<>());
        GameEngine engine = new GameEngine(levelSpecs);

        int maxTime = 10;
        for (int i=0; i<=maxTime; i++) {
            try {
                engine.executeNextScene(0.5);
            } catch (GameEngineException e) {
                System.out.println(e.getMessage()); //this should be something shown in the front-end.
            }
        }
    }
}
