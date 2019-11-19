package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.SpriteManager;

public class Level {

    private SpriteManager mySpriteManager;
    private WaveManager myWaveManager;
    private StatusManager myStatusManager;
    private ConditionsManager myConditionsManager;
    private ActionsManager myActionsManager;

    public Level(SpriteManager spriteManager, WaveManager waveManager, StatusManager statusManager, ConditionsManager conditionsManager, ActionsManager actionsManager) {
        mySpriteManager = spriteManager;
        myWaveManager = waveManager;
        myStatusManager = statusManager;
        myConditionsManager = conditionsManager;
        myActionsManager = actionsManager;
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        myStatusManager.notifyNewCycle(elapsedTime);
        myActionsManager.addNewActions(myConditionsManager.getActionsToExecute(this));
        myActionsManager.executeActions(this);
        mySpriteManager.getOnScreenSprites().stream().forEach(e -> e.move(elapsedTime));
        return new GameSceneObject(myStatusManager.getResources(),  myStatusManager.getLives(), mySpriteManager.getOnScreenSprites());
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public WaveManager getWaveManager() {
        return myWaveManager;
    }

    public StatusManager getTimeManager() {
        return myStatusManager;
    }
}
