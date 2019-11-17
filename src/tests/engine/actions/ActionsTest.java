package tests.engine.actions;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionsTest {

    SpriteManager spriteManager = new JavaFXSpriteManager();

    @BeforeEach
    public void setup() throws GameEngineException {
        Map<String, Object> prototype0HealthParameter = new HashMap<>() {{ put("health", 10); }};
        Map<String, Object> prototype1HealthParameter = new HashMap<>() {{ put("health", 15); }};

        HealthStrategy prototype0HealthStrategy = new Health(prototype0HealthParameter);
        HealthStrategy prototype1HealthStrategy = new Health(prototype1HealthParameter);

        Sprite prototype0 = new JavaFXSprite(0, 0, 0, prototype0HealthStrategy);
        Sprite prototype1 = new JavaFXSprite(0, 0, 0, prototype1HealthStrategy);
        spriteManager.addSpritePrototype(0, prototype0);
        spriteManager.addSpritePrototype(1, prototype1);
    }

    @Test
    public void testSpawnWaveAction() throws GameEngineException {
        Point wave0SpawnPoint = new Point(250, 300);
        Point wave1SpawnPoint = new Point(400, 400);

        LevelAction wave0SpawnAction = new SpawnWaveAction();
        LevelAction wave1SpawnAction = new SpawnWaveAction();

        Map<String, Object> condition0Parameter = new HashMap<>() {{ put("time", (double) 0); put("action", wave0SpawnAction); }};
        Map<String, Object> condition1Parameter = new HashMap<>() {{ put("time", (double) 3); put("action", wave1SpawnAction); }};
        LevelCondition condition0 = new TemporalCondition(condition0Parameter);
        LevelCondition condition1 = new TemporalCondition(condition1Parameter);

        Set<LevelCondition> levelConditionsSet = new HashSet<>() {{ add(condition0); add(condition1); }};

        Queue<Integer> spritesWave0Queue = new LinkedList<>() {{ add(0); add(1); add(0); }};
        Queue<Integer> spritesWave1Queue = new LinkedList<>() {{ add(1); add(0); add(1); }};
        Queue<Double> entryTimeWave0Queue = new LinkedList<>() {{ add(1.5); add(4.0); }};
        Queue<Double> entryTimeWave1Queue = new LinkedList<>() {{ add(1.0); add(5.0); }};

        Wave wave0 = new Wave(spritesWave0Queue, (Double) 1.0, wave0SpawnPoint);
        Wave wave1 = new Wave(spritesWave1Queue, (Double) 0.5, wave1SpawnPoint);
        Queue<Wave> wavesQueue = new LinkedList<>() {{ add(wave0); add(wave1); }};

        Level level = new Level(spriteManager, wavesQueue, levelConditionsSet);
        for (int i=0; i<20; i++) {
            level.execute(0.5);
            if (level.getTotalElapsedTime() == 3) {
                assertEquals(3, spriteManager.getOnScreenSprites().size());
            } else if (level.getTotalElapsedTime() == 4.5) {
                assertEquals(5, spriteManager.getOnScreenSprites().size());
            }
        }
        assertEquals(6, spriteManager.getOnScreenSprites().size());
    }
}
