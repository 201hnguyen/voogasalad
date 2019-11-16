package voogasalad;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.Wave;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.action.SpawnWaveAction;
import voogasalad.gameengine.engine.gamecontrol.condition.LevelCondition;
import voogasalad.gameengine.engine.gamecontrol.condition.AbsoluteTemporalCondition;
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
            LevelAction spawnWaveAction = new SpawnWaveAction();
            Map<String, Object> temporalConditionParameter = new HashMap<>() {{
                put("time", (double) 0);
                put("action", spawnWaveAction);
            }};
            LevelCondition levelCondition = new AbsoluteTemporalCondition(temporalConditionParameter);
            Set<LevelCondition> levelConditionList = new HashSet<>() {{
                add(levelCondition);
            }};

            Queue<Integer> spritesWaveQueue = new LinkedList<>() {{ add(0); add(1); add(0); }};
            Wave wave = new Wave(spritesWaveQueue, spawnPoint);
            Queue<Wave> wavesQueue = new LinkedList<>() {{ }};
            Level level = new Level(spriteManager, wavesQueue, levelConditionList);

            for (int i=0; i<5; i++) {
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
