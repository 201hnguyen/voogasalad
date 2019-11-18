package voogasalad.gameengine.engine.gamecontrol;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.TimeManager;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.SpriteManager;

public class Level {

    private SpriteManager mySpriteManager;
    private WaveManager myWaveManager;
    private TimeManager myTimeManager;
    private ConditionsManager myConditionsManager;
    private ActionsManager myActionsManager;

    public Level(SpriteManager spriteManager, WaveManager waveManager, TimeManager timeManager, ConditionsManager conditionsManager, ActionsManager actionsManager) {
        mySpriteManager = spriteManager;
        myWaveManager = waveManager;
        myTimeManager = timeManager;
        myConditionsManager = conditionsManager;
        myActionsManager = actionsManager;
    }

    public void execute(double elapsedTime) throws GameEngineException {
        myTimeManager.notifyNewCycle(elapsedTime);
        myActionsManager.addNewActions(myConditionsManager.getActionsToExecute(this));
        myActionsManager.executeActions(this);
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public WaveManager getWaveManager() {
        return myWaveManager;
    }

    public TimeManager getTimeManager() {
        return myTimeManager;
    }
}
