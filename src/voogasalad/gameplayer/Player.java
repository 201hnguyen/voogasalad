package voogasalad.gameplayer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.api.Engine;
import voogasalad.gameengine.executors.utils.SpriteArchetype;
import voogasalad.gameplayer.GUI.PlayerVisualization;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * PLEASE READ BEFORE MAKING CHANGES TO THE PLAYER PACKAGE:
 * DO NOT INSTANTIATE OR ACCESS ANY ENGINE OBJECT BESIDES THE ENGINE ITSELF WHEN TRYING TO DISPLAY IN THE PLAYER.
 * We currently do not have modules in the engine, but if/when we do, all other classes will be locked from outside access
 * to maintain API consistency. Currently, the classes you will have access to are: Engine, ActionsProcessor
 * (retrieve with a getter method once you have the engine), Sprite, and GameSceneObject. The engine and the UIActionProcessor
 * are essentially the input API for the game, and the GameSceneObject (with a list of Sprites and resources/
 * lives/stats/etc.) is the output API that is outputted by the engine at every execute level.
 * If you need something that we don't provide (e.g., a list of current tower prototypes), please Facebook message
 * someone from the engine team (Ha, Chris, and Emily) and we will make a getter method for you.
 */
public class Player {

    public static final int FRAMES_PER_SECOND = 10;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private Stage myStage;
    private PlayerVisualization myPlayerVisualization;
    private Engine myEngine;
    private Timeline myTimeline;
    private GameSceneObject myCurrentGameSceneObject;
    private HashMap<String, Integer> gameInfo;

    //Player expects a javaFX Stage upon instantiation
    public Player(Stage primaryStage, Document doc) throws GameEngineException { //TODO: Don't throw GameEngineException out of Player
        myStage = primaryStage;
        myEngine = new Engine(doc);
        startGame();
    }

    private void startGame() {
        myTimeline = new Timeline();
        myPlayerVisualization = new PlayerVisualization(myStage, myTimeline, myEngine.getActionsProcessor(), this);
        setGameLoop();
    }

    private void gameLoop(double elapsedTime) throws GameEngineException {
        if(myEngine.didLevelSwitch()) {
            myCurrentGameSceneObject = myEngine.execute(0);
            gameInfo = new HashMap<>();
            gameInfo.put("Lives", myCurrentGameSceneObject.getLives());
            gameInfo.put("Coins", myCurrentGameSceneObject.getResources());
            myPlayerVisualization.setNewLevel(myEngine.getSpritePrototypesByArchetype(SpriteArchetype.TOWER), myEngine.getSpritePrototypesByArchetype(SpriteArchetype.ENEMY), myEngine.getCurrentLevelBackgroundPath(), gameInfo);
            myTimeline.pause();
        }
        else {
            myCurrentGameSceneObject = myEngine.execute(elapsedTime);
            gameInfo.put("Lives", myCurrentGameSceneObject.getLives());
            gameInfo.put("Coins", myCurrentGameSceneObject.getResources());
            myPlayerVisualization.update(myCurrentGameSceneObject.getOnScreenSprites(), gameInfo);
        }
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
        myTimeline.play();
    }

    public void executeEngineWithZeroElapsedTime() {
        try {
            myEngine.execute(0);
        }
        catch (GameEngineException ex){
            ex.printStackTrace(); //TODO: Fix
        }
    }

}

