package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.sprites.SpriteManager;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Level {

    SpriteManager mySpriteManager;
    Queue<Wave> mySpritesWavesQueue;
    double myTotalElapsedTime;
    Set<LevelCondition> myLevelConditions;
    Set<LevelAction> myActionsInProgress;

    public Level(SpriteManager spriteManager, Queue<Wave> spritesWaveQueue, Set<LevelCondition> levelConditions) throws GameEngineException {
        mySpriteManager = spriteManager;
        mySpritesWavesQueue = spritesWaveQueue;
        myTotalElapsedTime = 0;
        myLevelConditions = levelConditions;
        myActionsInProgress = new HashSet<>();
    }

    public void execute(double elapsedTime) throws GameEngineException {
        myTotalElapsedTime += elapsedTime;
        checkLevelConditions();
        executeActions();
    }

    private void checkLevelConditions(){
        Set<LevelCondition> conditionsToRemove = new HashSet<>();
        for (LevelCondition condition : myLevelConditions) {
            if (condition.hasHappened(this)) {
                conditionsToRemove.add(condition);
                LevelAction action = condition.getAction();
                myActionsInProgress.add(action);
            }
        }
        for (LevelCondition condition : conditionsToRemove){
            myLevelConditions.remove(condition);
        }
    }

    private void executeActions() throws GameEngineException{
        Set<LevelAction> actionsToRemove = new HashSet<>();
        for (LevelAction action : myActionsInProgress) {
            action.execute(this);
            if (action.isFinished()) {
                actionsToRemove.add(action);
            }
        }
        for (LevelAction action : actionsToRemove) {
            myActionsInProgress.remove(action);
        }
    }

    public double getElapsedTime() {
        System.out.println("Level elapsed time:" + myTotalElapsedTime);
        return myTotalElapsedTime;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public Wave getNextWave() {
        return mySpritesWavesQueue.remove();
    }

    public boolean hasNextWave() {
        return mySpritesWavesQueue.size() > 0;
    }
}
