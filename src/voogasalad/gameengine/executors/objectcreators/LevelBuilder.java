package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.gamecontrol.Wave;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.executors.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.executors.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.Collection;
import java.util.List;

public class LevelBuilder {

    private StatusManager myStatusManager;
    private SpriteManager mySpriteManager;
    private WaveManager myWaveManager;
    private ActionsManager myActionsManager;
    private ConditionsManager myConditionsManager;
    private SpriteProductsFactory mySpriteProductsFactory;
    private int myLevelId;
    private String myBackgroundPath;
    private LevelActionsRequester myLevelActionsRequester;

    public LevelBuilder(int id) throws GameEngineException {
        myLevelId = id;
        mySpriteProductsFactory = new SpriteProductsFactory();
        myLevelActionsRequester = new LevelActionsRequester();
        myStatusManager = new StatusManager();
        myWaveManager = new WaveManager();
        myActionsManager = new ActionsManager();
        myConditionsManager = new ConditionsManager();
        mySpriteManager = mySpriteProductsFactory.makeSpriteManager(myLevelActionsRequester);
    }

    public String getBackgroundPath() {
        return myBackgroundPath;
    }

    public LevelActionsRequester getLevelActionsRequester() {
        return myLevelActionsRequester;
    }

    public StatusManager getStatusManager() {
        return myStatusManager;
    }

    public WaveManager getWaveManager() {
        return myWaveManager;
    }

    public ActionsManager getActionsManager() {
        return myActionsManager;
    }

    public ConditionsManager getConditionsManager() {
        return myConditionsManager;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public int getLevelId() {
        return myLevelId;
    }

    public LevelBuilder setBackgroundPath(String backgroundPath) {
        myBackgroundPath = backgroundPath;
        return this;
    }

    public LevelBuilder setWaves(Collection<Wave> waves) {
        myWaveManager.addWavesCollection(waves);
        return this;
    }

    public LevelBuilder setResources(int resources) {
        myStatusManager.setResources(resources);
        return this;
    }

    public LevelBuilder setLives(int lives) {
        myStatusManager.setLives(lives);
        return this;
    }

    public LevelBuilder setConditions(Collection<LevelCondition> conditions) {
        myConditionsManager.addConditionCollection(conditions);
        return this;
    }

    public LevelBuilder setSpritePrototypes(List<Sprite> spritePrototypes) {
        for (Sprite sprite : spritePrototypes) {
            mySpriteManager.addSpritePrototype(sprite);
        }
        return this;
    }

    public Level build() {
        return new Level(this);
    }

}
