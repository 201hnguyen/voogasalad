package voogasalad.gameengine.engine;

import voogasalad.gameengine.engine.elements.Level;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.playerengineapi.specs.LevelSpecs;

public class GameEngine {

    private LevelSpecs myLevelSpecs;
    private Level myCurrentLevel;
    private double myTotalTimePassed;

    public GameEngine(LevelSpecs levelSpecs) {
        myLevelSpecs = levelSpecs;
        myTotalTimePassed = 0;
    }

    public void executeNextScene(double elapsedTime) throws GameEngineException {
        // check events --> implement actions
        // check conditions --> implement actions
        // check currently happening actions????
        myTotalTimePassed+= elapsedTime;
        System.out.println("CURRENT TIME: " + myTotalTimePassed);
        if (myTotalTimePassed == 0.5) { // this is basically a condition of the like that you check for
            myCurrentLevel = new Level(myLevelSpecs);
        }
        myCurrentLevel.executeNextScene();
    }
}
