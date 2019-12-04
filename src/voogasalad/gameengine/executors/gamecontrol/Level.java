package voogasalad.gameengine.executors.gamecontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.executors.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.SpriteManager;

public class Level implements GameScene {

    private SpriteManager mySpriteManager;
    private WaveManager myWaveManager;
    private StatusManager myStatusManager;
    private ConditionsManager myConditionsManager;
    private ActionsManager myActionsManager;
    private int myLevelId;
    private String myBackgroundPath;
    private LevelActionsRequester myActionsRequester;

    public Level(LevelBuilder levelBuilder) {
        myLevelId = levelBuilder.getLevelId();
        mySpriteManager = levelBuilder.getSpriteManager();
        myWaveManager = levelBuilder.getWaveManager();
        myStatusManager = levelBuilder.getStatusManager();
        myConditionsManager = levelBuilder.getConditionsManager();
        myActionsManager = levelBuilder.getActionsManager();
        myBackgroundPath = levelBuilder.getBackgroundPath();
        myActionsRequester = levelBuilder.getLevelActionsRequester();
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        myStatusManager.notifyNewCycle(elapsedTime);
        myActionsManager.addActionsAsCollection(myActionsRequester.getRequestedActions());
        myActionsManager.addActionsAsCollection(myConditionsManager.getActionsToExecute(this));
        myActionsManager.executeActions(this);
        mySpriteManager.executeSpriteNextState(elapsedTime);
        return new GameSceneObject(myBackgroundPath, myStatusManager.getResources(),  myStatusManager.getLives(), mySpriteManager);
    }

    public LevelActionsRequester getActionsRequester() {
        return myActionsRequester;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public WaveManager getWaveManager() {
        return myWaveManager;
    }

    public StatusManager getStatusManager() {
        return myStatusManager;
    }

    public int getLevelId() {
        return myLevelId;
    }
}
