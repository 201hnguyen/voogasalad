package voogasalad.gameplayer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.api.Engine;
import voogasalad.gameengine.executors.utils.SpriteArchetype;
import voogasalad.gameplayer.GUI.PlayerVisualization;

/**
 * PLEASE READ BEFORE MAKING CHANGES TO THE PLAYER PACKAGE:
 * DO NOT INSTANTIATE OR ACCESS ANY ENGINE OBJECT BESIDES THE ENGINE ITSELF WHEN TRYING TO DISPLAY IN THE PLAYER.
 * We currently do not have modules in the engine, but if/when we do, all other classes will be locked from outside access
 * to maintain API consistency. Currently, the classes you will have access to are: Engine, UIActionsProcessor
 * (retrieve with a getter method once you have the engine), Sprite, and GameSceneObject. The engine and the UIActionProcessor
 * are essentially the input API for the game, and the GameSceneObject (with a list of Sprites and resources/
 * lives/stats/etc.) is the output API that is outputted by the engine at every execute level.
 * If you need something that we don't provide (e.g., a list of current tower prototypes), please Facebook message
 * someone from the engine team (Ha, Chris, and Emily) and we will make a getter method for you.
 */
public class Player {

    public static final int WINDOW_SIZE = 500;
    public static final int FRAMES_PER_SECOND = 40;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Stage myStage;
    private Group myMapRoot;
    private PlayerVisualization myPlayerVisualization;
    private Engine myEngine;
    private Timeline myTimeline;
    private GameSceneObject myCurrentGameSceneObject;

    //Player expects a javaFX Stage upon instantiation
    public Player(Stage primaryStage, Document doc) throws GameEngineException { //TODO: Don't throw GameEngineException out of Player
        myStage = primaryStage;
        myMapRoot = new Group();
        myEngine = new Engine(doc);
        startGame();
    }

    public void startGame() throws GameEngineException {
        myTimeline = new Timeline();
        myPlayerVisualization = new PlayerVisualization(myStage, myTimeline);
        setGameLoop();
    }

    private void gameLoop(double elapsedTime) throws GameEngineException {
        if(myEngine.didLevelSwitch()) {
            System.out.println("level switched");
            myPlayerVisualization.setNewLevel(myEngine.getSpritePrototypesByArchetype(SpriteArchetype.TOWER), myEngine.getCurrentLevelBackgroundPath());
        }
        myCurrentGameSceneObject = myEngine.execute(elapsedTime);
        myPlayerVisualization.update(myCurrentGameSceneObject.getOnScreenSprites());
    }

    private void setGameLoop() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
            try {
                gameLoop(SECOND_DELAY);
            } catch (GameEngineException ex) {
                ex.printStackTrace(); //TODO: Fix
            }
        });
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(frame);
    }

}

