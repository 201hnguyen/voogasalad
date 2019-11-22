package voogasalad.gameengine.configurators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.gamecontrol.Wave;
import voogasalad.gameengine.executors.gamecontrol.action.LevelAction;
import voogasalad.gameengine.executors.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.executors.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.executors.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.executors.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.*;

public class EngineDriverManager {


    private List<Wave> waveList;
    private Set<LevelCondition> levelConditions;
    private List<LevelAction> levelActions;
    private int resources;
    private int lives;
    private Map<Integer, Sprite> mySpritePrototypes;


    public EngineDriverManager(){
        mySpritePrototypes = new HashMap<>();
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

    public Level getNewLevel() throws GameEngineException {
        return new LevelBuilder().
                setConditions(levelConditions).setLives(lives).setResources(resources).setWaves(waveList).setSpritePrototypes(mySpritePrototypes).build();
    }

    public void addSpritePrototype(int prototypeId, Sprite sprite) {
        mySpritePrototypes.put(prototypeId, sprite);
    }
}
