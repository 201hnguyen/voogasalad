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
import voogasalad.gameengine.engine.gamecontrol.managers.ActionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.ConditionsManager;
import voogasalad.gameengine.engine.gamecontrol.managers.StatusManager;
import voogasalad.gameengine.engine.gamecontrol.managers.WaveManager;
import voogasalad.gameengine.engine.sprites.*;
import voogasalad.gameengine.engine.sprites.strategies.health.Health;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.engine.sprites.strategies.movement.PathMovement;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionsTest {

    SpriteManager spriteManager = new JavaFXSpriteManager();

    @BeforeEach
    public void setup() throws GameEngineException {
        Map<String, Object> prototype0HealthParameter = new HashMap<>() {{ put("health", 10); }};
        Map<String, Object> prototype1HealthParameter = new HashMap<>() {{ put("health", 15); }};
        Map<String, Object> movementParameters = new HashMap<>() {{
            put("path", new LinkedList<>());
            put("speed", 10.0);
        }};

        HealthStrategy prototype0HealthStrategy = new Health(prototype0HealthParameter);
        HealthStrategy prototype1HealthStrategy = new Health(prototype1HealthParameter);
        MovementStrategy prototypeMovementStrategy = new PathMovement(movementParameters);

        Sprite prototype0 = new SpriteBuilder()
                .setWidth(50)
                .setHeight(50)
                .setImagePath("pandaslogo.png")
                .setHealthStrategy(prototype0HealthStrategy)
                .setMovementStrategy(prototypeMovementStrategy)
                .build();

        Sprite prototype1 = new SpriteBuilder()
                .setWidth(50)
                .setHeight(50)
                .setImagePath("pandaslogo.png")
                .setHealthStrategy(prototype1HealthStrategy)
                .setMovementStrategy(prototypeMovementStrategy)
                .build();

        spriteManager.addSpritePrototype(0, prototype0);
        spriteManager.addSpritePrototype(1, prototype1);
    }

    @Test
    public void testSpawnWaveAction() throws GameEngineException {
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
        StatusManager statusManager = new StatusManager(250, 20);
        ConditionsManager conditionsManager = new ConditionsManager(levelConditionsSet);
        ActionsManager actionsManager = new ActionsManager();

        Level level = new Level(spriteManager, waveManager, statusManager, conditionsManager, actionsManager);

        for (int i=0; i<20; i++) {
            level.execute(0.5);
            if (level.getStatusManager().getTotalElapsedTime() == 2) {
                assertEquals(2, spriteManager.getOnScreenSprites().size());
            } else if (level.getStatusManager().getTotalElapsedTime() == 3.5) {
                assertEquals(5, spriteManager.getOnScreenSprites().size());
            }
        }
        assertEquals(6, spriteManager.getOnScreenSprites().size());
    }
}
