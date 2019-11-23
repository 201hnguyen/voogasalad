package voogasalad.gameengine.executors.gamecontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.executors.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.SpriteManager;
import voogasalad.gameengine.configurators.EngineConfigurator;

public class Level {

    private SpriteManager mySpriteManager;
    private WaveManager myWaveManager;
    private StatusManager myStatusManager;
    private ConditionsManager myConditionsManager;
    private ActionsManager myActionsManager;

    public Level(LevelBuilder levelBuilder) {
        mySpriteManager = levelBuilder.getSpriteManager();
        myWaveManager = levelBuilder.getWaveManager();
        myStatusManager = levelBuilder.getStatusManager();
        myConditionsManager = levelBuilder.getConditionsManager();
        myActionsManager = levelBuilder.getActionsManager();
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        myStatusManager.notifyNewCycle(elapsedTime);
        myActionsManager.addActionsAsCollection(myConditionsManager.getActionsToExecute(this));
        myActionsManager.executeActions(this);
        mySpriteManager.getOnScreenSprites().stream().forEach((sprite -> sprite.updatePosition(elapsedTime)));
        return new GameSceneObject(myStatusManager.getResources(),  myStatusManager.getLives(), mySpriteManager);
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

    public void addAction(LevelAction action) {
        myActionsManager.addAction(action);
    }
}
