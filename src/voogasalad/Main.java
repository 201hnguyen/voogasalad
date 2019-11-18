package voogasalad;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.action.SpawnWaveAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.gamecontrol.condition.TemporalCondition;
import voogasalad.gameengine.engine.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.TimeManager;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.JavaFXSprite;
import voogasalad.gameengine.engine.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteManager;
import voogasalad.gameengine.engine.sprites.strategies.health.Health;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

import java.awt.Point;
import java.util.*;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        try {
            Map<String, Object> prototype0HealthParameter = new HashMap<>() {{ put("health", 10); }};
            Map<String, Object> prototype1HealthParameter = new HashMap<>() {{ put("health", 15); }};

            HealthStrategy prototype0HealthStrategy = new Health(prototype0HealthParameter);
            HealthStrategy prototype1HealthStrategy = new Health(prototype1HealthParameter);

            SpriteManager spriteManager = new JavaFXSpriteManager();
            Sprite prototype0 = new JavaFXSprite(0, 0, 0, prototype0HealthStrategy);
            Sprite prototype1 = new JavaFXSprite(0, 0, 0, prototype1HealthStrategy);
            spriteManager.addSpritePrototype(0, prototype0);
            spriteManager.addSpritePrototype(1, prototype1);

            Point wave0SpawnPoint = new Point(250, 300);
            Point wave1SpawnPoint = new Point(400, 400);

            LevelAction wave0SpawnAction = new SpawnWaveAction();
            LevelAction wave1SpawnAction = new SpawnWaveAction();

            Set<LevelAction> levelActions = new HashSet<>();
            Set<LevelAction> levelActions1 = new HashSet<>();
            levelActions.add(wave0SpawnAction);
            levelActions1.add(wave1SpawnAction);
            System.out.println(levelActions.getClass().getName());

            Map<String, Object> condition0Parameter = new HashMap<>() {{ put("time", (double) 0); put("action", levelActions); }};
            Map<String, Object> condition1Parameter = new HashMap<>() {{ put("time", (double) 3); put("action", levelActions1); }};
            LevelCondition condition0 = new TemporalCondition(condition0Parameter);
            LevelCondition condition1 = new TemporalCondition(condition1Parameter);

            Set<LevelCondition> levelConditionsSet = new HashSet<>() {{ add(condition0); add(condition1); }};

            Queue<Integer> spritesWave0Queue = new LinkedList<>() {{ add(0); add(1); add(0); }};
            Queue<Integer> spritesWave1Queue = new LinkedList<>() {{ add(1); add(0); add(1); }};

            Wave wave0 = new Wave(spritesWave0Queue, 1.0, wave0SpawnPoint);
            Wave wave1 = new Wave(spritesWave1Queue, 0.5, wave1SpawnPoint);
            List<Wave> wavesList = new ArrayList<>() {{ add(wave0); add(wave1); }};

            WaveManager waveManager = new WaveManager(wavesList);
            TimeManager timeManager = new TimeManager();
            ConditionsManager conditionsManager = new ConditionsManager(levelConditionsSet);
            ActionsManager actionsManager = new ActionsManager();

            Level level = new Level(spriteManager, waveManager, timeManager, conditionsManager, actionsManager);

            for (int i=0; i<20; i++) {
                System.out.println("new clock tick");
                level.execute(0.5);
                for (Sprite sprite : level.getSpriteManager().getOnScreenSprites()) {
                    System.out.println("Sprite generated:" + " id: " + sprite.getId() + " health: " + sprite.getHealth() + " xPos: " + sprite.getX() + " yPos:" + sprite.getY());
                }
            }
        } catch (GameEngineException e) {

        }
    }
}
