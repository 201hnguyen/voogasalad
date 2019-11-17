package voogasalad;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.action.SpawnWaveAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.gamecontrol.condition.TemporalCondition;
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
            Map<String, Object> healthParameter = new HashMap<>() {{
                put("health", 10);
            }};
            HealthStrategy healthStrategy = new Health(healthParameter);
            Sprite spritePrototype = new JavaFXSprite(0, 0, 0, healthStrategy);
            SpriteManager spriteManager = new JavaFXSpriteManager();
            spriteManager.addSpritePrototype(0, spritePrototype);
            spriteManager.addSpritePrototype(1, spritePrototype);

            Point spawnPoint = new Point(250, 300);
            Point spawnPoint2 = new Point(400, 400);
            LevelAction spawnWaveAction = new SpawnWaveAction();
            LevelAction spawnWaveAction2 = new SpawnWaveAction();
            Map<String, Object> temporalConditionParameter = new HashMap<>() {{
                put("time", (double) 0);
                put("action", spawnWaveAction);
            }};

            Map<String, Object> temporalConditionParameter2 = new HashMap<>() {{
                put("time", (double) 3);
                put("action", spawnWaveAction2);
            }};

            LevelCondition levelCondition = new TemporalCondition(temporalConditionParameter);
            LevelCondition levelCondition2 = new TemporalCondition(temporalConditionParameter2);

            Set<LevelCondition> levelConditionList = new HashSet<>() {{
                add(levelCondition);
                add(levelCondition2);
            }};

            Queue<Integer> spritesWaveQueue = new LinkedList<>() {{ add(0); add(1); add(0); }};
            Queue<Integer> spritesWaveQueue2 = new LinkedList<>() {{ add(0); add(1); add(0); }};

            Queue<Integer> spritesEntryTimeQueue = new LinkedList<>() {{ add(2); add(4); }};
            Queue<Integer> spritesEntryTimeQueue2 = new LinkedList<>() {{ add(2); add(5); }};
            Wave wave = new Wave(spritesWaveQueue, spritesEntryTimeQueue, spawnPoint);
            Wave wave2 = new Wave(spritesWaveQueue2, spritesEntryTimeQueue2, spawnPoint2);
            Queue<Wave> wavesQueue = new LinkedList<>() {{ add(wave); add(wave2); }};
            Level level = new Level(spriteManager, wavesQueue, levelConditionList);

            for (int i=0; i<7; i++) {
                System.out.println("new clock tick");
                level.execute(1);
                for (Sprite sprite : level.getSpriteManager().getOnScreenSprites()) {
                    System.out.println("Sprite generated:" + " id: " + sprite.getId() + " health: " + sprite.getHealth() + " xPos: " + sprite.getX() + " yPos:" + sprite.getY());
                }
            }
        } catch (GameEngineException e) {

        }
    }
}
