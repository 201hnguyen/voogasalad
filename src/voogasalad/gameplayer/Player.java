package voogasalad.gameplayer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Engine;
import voogasalad.gameplayer.GUI.PlayerVisualization;

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
    public Player(Stage primaryStage, Document doc) throws GameEngineException {
        myStage = primaryStage;
        myMapRoot = new Group();
        myEngine = new Engine();
        myEngine.configureGame(doc);
        startGame();
    }

    public void startGame() {
        myTimeline = new Timeline();
        myPlayerVisualization = new PlayerVisualization(myStage, myEngine.getSpritePrototypes(), myTimeline);
        setGameLoop();
    }

    private void gameLoop(double elapsed_time) throws GameEngineException {
        myCurrentGameSceneObject = myEngine.execute(elapsed_time);
        myPlayerVisualization.update(myCurrentGameSceneObject.getOnScreenSprites());
    }

    private void setGameLoop() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
            try {
                gameLoop(SECOND_DELAY);
            } catch (GameEngineException ex) {
                ex.printStackTrace();
            }
        });
        myTimeline.setCycleCount(Timeline.INDEFINITE);
        myTimeline.getKeyFrames().add(frame);
    }

}

