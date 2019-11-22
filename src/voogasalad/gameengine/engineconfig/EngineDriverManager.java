package voogasalad.gameengine.engineconfig;

import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EngineDriverManager {
    private SpriteManager spriteManager;
    private WaveManager waveManager;
    private ActionsManager actionsManager;
    private ConditionsManager conditionsManager;
    private StatusManager statusManager;


    private List<Wave> waveList;
    private Set<LevelCondition> levelConditions;
    private List<LevelAction> levelActions;
    private int resources;
    private int lives;


    public EngineDriverManager(){
        spriteManager = new JavaFXSpriteManager();
        actionsManager = new ActionsManager();
        waveList = new ArrayList<>();
        levelConditions = new HashSet<>();
        levelActions = new ArrayList<>();
        resources = 0;
        lives = 0;
    }

    public void addWave(Wave wave){
        waveList.add(wave);
    }

    public void addLevelAction(LevelAction action){
        levelActions.add(action);
    }

    public void addLevelCondition(LevelCondition condition){
        levelConditions.add(condition);
    }

    public void setResourcesAndLives(int resources, int lives){
        this.resources = resources;
        this.lives = lives;
    }

    //Must use add API methods to first populate waveList and levelConditions
    public void instantiateEngineManagers(){
        waveManager = new WaveManager(waveList);
        conditionsManager = new ConditionsManager(levelConditions);
        statusManager = new StatusManager(resources, lives);
    }

    public Level getNewLevel(){
        return new Level(spriteManager, waveManager, statusManager, conditionsManager, actionsManager);
    }

    public void addSpritePrototype(int prototypeId, Sprite sprite) {
        spriteManager.addSpritePrototype(prototypeId, sprite);
    }
}
