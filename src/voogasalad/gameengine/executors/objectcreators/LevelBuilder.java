package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
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
import java.util.Map;

public class LevelBuilder {

    private StatusManager myStatusManager;
    private SpriteManager mySpriteManager;
    private WaveManager myWaveManager;
    private ActionsManager myActionsManager;
    private ConditionsManager myConditionsManager;
    private SpriteProductsFactory mySpriteProductsFactory;

    public LevelBuilder() throws GameEngineException {
        mySpriteProductsFactory = new SpriteProductsFactory();
        myStatusManager = new StatusManager();
        myWaveManager = new WaveManager();
        myActionsManager = new ActionsManager();
        myConditionsManager = new ConditionsManager();
        mySpriteManager = mySpriteProductsFactory.makeSpriteManager();
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
