package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.sprites.SpriteManager;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Level {

    //TODO: May need to break up this class; it's on the path to being pretty big and doing a lot of things.
    private SpriteManager mySpriteManager;
    private Queue<Wave> mySpritesWavesQueue;
    private double myTotalElapsedTime;
    private double myElapsedTimeSinceLastFrame;
    private Set<LevelCondition> myLevelConditions;
    private Set<LevelAction> myActionsInProgress;

    public Level(SpriteManager spriteManager, Queue<Wave> spritesWavesQueue, Set<LevelCondition> levelConditions) {
        mySpriteManager = spriteManager;
        mySpritesWavesQueue = spritesWavesQueue;
        myTotalElapsedTime = 0;
        myElapsedTimeSinceLastFrame = 0;
        myLevelConditions = levelConditions;
        myActionsInProgress = new HashSet<>();
    }

    public void execute(double elapsedTime) throws GameEngineException {
        myElapsedTimeSinceLastFrame = elapsedTime;
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

    public double getTotalElapsedTime() {
        System.out.println("Level elapsed time:" + myTotalElapsedTime);
        return myTotalElapsedTime;
    }

    public double getElapsedTimeSinceLastFrame() {
        return myElapsedTimeSinceLastFrame;
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
