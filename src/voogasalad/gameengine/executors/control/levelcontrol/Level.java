package voogasalad.gameengine.executors.control.levelcontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelConditionsManager;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelStatusManager;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelWaveManager;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelActionsManager;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.Set;

public class Level implements GameScene {

    private SpriteManager mySpriteManager;
    private LevelWaveManager myLevelWaveManager;
    private LevelStatusManager myLevelStatusManager;
    private LevelConditionsManager myLevelConditionsManager;
    private LevelActionsManager myLevelActionsManager;
    private int myLevelId;
    private String myBackgroundPath;
    private LevelActionsRequester myActionsRequester;

    public Level(LevelBuilder levelBuilder) {
        myLevelId = levelBuilder.getLevelId();
        mySpriteManager = levelBuilder.getSpriteManager();
        myLevelWaveManager = levelBuilder.getWaveManager();
        myLevelStatusManager = levelBuilder.getStatusManager();
        myLevelConditionsManager = levelBuilder.getConditionsManager();
        myLevelActionsManager = levelBuilder.getActionsManager();
        myBackgroundPath = levelBuilder.getBackgroundPath();
        myActionsRequester = levelBuilder.getLevelActionsRequester();
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        mySpriteManager.executeSpriteNextState(elapsedTime);
        myLevelStatusManager.notifyNewCycle(elapsedTime);
        myLevelActionsManager.addLevelActionsAsCollection(myActionsRequester.getRequestedActions());
        myLevelActionsManager.addLevelActionsAsCollection(myLevelConditionsManager.getLevelActionsToExecute(this));
        myLevelActionsManager.executeLevelActions(this);
        return new GameSceneObject(myBackgroundPath, myLevelStatusManager.getCopyOfStatusManager(), mySpriteManager);
    }

    public String getBackgroundPath() {
        return myBackgroundPath;
    }

    public LevelActionsRequester getActionsRequester() {
        return myActionsRequester;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public LevelWaveManager getWaveManager() {
        return myLevelWaveManager;
    }

    public LevelStatusManager getStatusManager() {
        return myLevelStatusManager;
    }

    public int getLevelId() {
        return myLevelId;
    }

    public int getCurrentScore() {
        return myLevelStatusManager.getScore();
    }

    public Set<LevelCondition> getLevelConditions() {
        return myLevelConditionsManager.getLevelConditions();
    }
}
