package voogasalad;

import javafx.application.Application;
import javafx.stage.Stage;
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
import voogasalad.gameengine.engine.sprites.JavaFXSprite;
import voogasalad.gameengine.engine.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engine.sprites.SpriteManager;
import voogasalad.gameengine.engine.sprites.strategies.health.Health;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameplayer.GUI.PlayerVisualization;
import voogasalad.gameplayer.Player;

import java.awt.Point;
import java.util.*;


public class Main extends Application {

    public static final String XML = "src/resources/player/MockData.xml";
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Player player = new Player(primaryStage, XML);
            Level level = player.startGame();
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
