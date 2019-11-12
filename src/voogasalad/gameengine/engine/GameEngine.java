package voogasalad.gameengine.engine;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;

public class GameEngine {

    private LevelSpecs myLevelSpecs;
    private Level myCurrentLevel;

    public GameEngine(LevelSpecs levelSpecs) {
        myLevelSpecs = levelSpecs;
    }

    public void executeNextScene(int elapsedTime) throws GameEngineException {
        // check events --> implement actions
        // check conditions --> implement actions
        if (elapsedTime == 0) { // this is basically a condition of the like that you check for
            myCurrentLevel = new Level(myLevelSpecs);
        }
        myCurrentLevel.executeNextScene();
    }
}
