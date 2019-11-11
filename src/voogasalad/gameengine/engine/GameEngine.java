package voogasalad.gameengine.engine;

import voogasalad.gameengine.engine.action.auto.SpawnSpritesAutoAction;
import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;

public class GameEngine {

    private Level myLevel;

    public GameEngine() {

    }

    public void createNewLevel(LevelSpecs levelSpecs) {
        myLevel = new Level(levelSpecs);
    }

    public void executeNextScene(int elapsedTime) {
        //TODO: Check conditions, then decide what to do for this time
        SpawnSpritesAutoAction spawnSpritesAction = new SpawnSpritesAutoAction();
        spawnSpritesAction.executeAction(myLevel);
    }
}
