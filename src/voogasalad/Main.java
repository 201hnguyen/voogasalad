package voogasalad;

import bus.Bus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameplayer.Player;


public class Main extends Application {

    public static final String TITLE = "Game Authoring Environment";
    public static final String XML = "src/resources/player/MockData.xml";
    private static Bus myBus;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        BorderPane root = new BorderPane();
//        primaryStage.setTitle(TITLE);
//        primaryStage.setScene(setBusScene(primaryStage, root));
//        primaryStage.show();
//        myBus.changeToGamePlayer(primaryStage);
        Player player = new Player(primaryStage, XML);
    }

//    private Scene setBusScene(Stage currentStage, BorderPane root){
//        myBus = new Bus(currentStage, root, 500, 500);
//        return myBus.getBusScene();
//    }
}


