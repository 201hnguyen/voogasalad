package voogasalad;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameplayer.Player;


public class Main extends Application {

    public static final String XML = "src/resources/player/MockData.xml";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Player player = new Player(primaryStage, XML);
        player.startGame();
    }
}


