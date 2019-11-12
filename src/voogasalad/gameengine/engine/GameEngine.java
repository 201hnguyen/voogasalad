package voogasalad.gameengine.engine;

import voogasalad.gameengine.engine.action.auto.SpawnSpritesAutoAction;
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
        //TODO: Check conditions and figure out what to do
        if (elapsedTime == 0) { // this is basically a condition of the like that you check for
            myCurrentLevel = new Level(myLevelSpecs, elapsedTime);
        }

        SpawnSpritesAutoAction spawnSpritesAction = new SpawnSpritesAutoAction();
        spawnSpritesAction.executeAction(myCurrentLevel);
    }
}
