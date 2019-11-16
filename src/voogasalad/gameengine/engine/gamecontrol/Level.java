package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.sprites.SpriteManager;

import java.awt.Point;
import java.util.*;

public class Level {

    SpriteManager mySpriteManager;
    Queue<Wave> mySpritesWavesQueue;
    double myElapsedTime;
    Set<LevelCondition> myLevelConditions;
    Set<LevelAction> myActionsInProgress;

    public Level(SpriteManager spriteManager, Queue<Wave> spritesWaveQueue, Set<LevelCondition> levelConditions) throws GameEngineException {
        mySpriteManager = spriteManager;
        mySpritesWavesQueue = spritesWaveQueue;
        myElapsedTime = 0;
        myLevelConditions = levelConditions;
        myActionsInProgress = new HashSet<>();
    }

    public void execute(double elapsedTime) throws GameEngineException {
        myElapsedTime += elapsedTime;
        executeActionsInProgress();
        checkLevelConditions();
    }

    private void checkLevelConditions() throws GameEngineException {
        List<LevelCondition> conditionsToRemove = new ArrayList<>();
        for (LevelCondition condition : myLevelConditions) {
            if (condition.hasHappened(this)) {
                conditionsToRemove.add(condition);
                LevelAction action = condition.getAction();
                executeAction(action);
            }
        }
        for (LevelCondition condition : conditionsToRemove){
            myLevelConditions.remove(condition);
        }
    }

    private void executeAction(LevelAction action) throws GameEngineException{
        action.execute(this);
        if (!action.isFinished()) {
            myActionsInProgress.add(action);
        }
    }

    private void executeActionsInProgress() throws GameEngineException{
        List<LevelAction> actionsToRemove = new ArrayList<>();
        for (LevelAction action : myActionsInProgress) {
            executeAction(action);
            if (action.isFinished()) {
                actionsToRemove.add(action);
            }
        }
        for (LevelAction action : actionsToRemove) {
            myActionsInProgress.remove(action);
        }
    }

    public double getElapsedTime() {
        return myElapsedTime;
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
