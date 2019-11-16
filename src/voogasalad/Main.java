package voogasalad;

import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.gamecontrol.action.LevelAction;
import voogasalad.gameengine.engine.gamecontrol.action.SpawnSpriteAction;
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
    public static void main (String[] args) throws GameEngineException {
        Map<String, Object> healthParameter = new HashMap<>() {{ put("health", 10); }};
        HealthStrategy healthStrategy = new Health(healthParameter);
        Sprite spritePrototype = new JavaFXSprite(0, 0, 0, healthStrategy);
        SpriteManager spriteManager = new JavaFXSpriteManager();
        spriteManager.addSpritePrototype(0, spritePrototype);
        spriteManager.addSpritePrototype(1, spritePrototype);
        Queue<Integer> spritesQueue = new LinkedList<>() {{ add(0); add(1); }};
        Point spawnPoint = new Point(250, 300);
        LevelAction spawnSpriteAction = new SpawnSpriteAction();
        Map<String, Object> temporalConditionParameter = new HashMap<>() {{ put("time", (double) 0); put("action", spawnSpriteAction); }};
        Map<String, Object> temporalConditionParameter2 = new HashMap<>() {{ put("time", (double) 1); put("action", spawnSpriteAction); }};
        LevelCondition levelCondition = new TemporalCondition(temporalConditionParameter);
        LevelCondition levelCondition2 = new TemporalCondition(temporalConditionParameter2);
        List<LevelCondition> levelConditionList = new ArrayList<>() {{ add(levelCondition); add(levelCondition2); }};
        Level level = new Level(spriteManager, spritesQueue, spawnPoint, levelConditionList);
        level.execute(0);
        level.execute(1);
        for (Sprite sprite : level.getSpriteManager().getOnScreenSprites()) {
            System.out.println("Sprite generated:" + " id: " + sprite.getId() + " health: "+ sprite.getHealth() + " xPos: "+ sprite.getX() + " yPos:" + sprite.getY());
        }
    }
}
