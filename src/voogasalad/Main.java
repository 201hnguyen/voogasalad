package voogasalad;

import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.gamecontrol.Level;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameplayer.Player;


public class Main extends Application {

    public static final String XML = "src/resources/player/MockData.xml";
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        PlayerVisualization playerVisualization = new PlayerVisualization(primaryStage);
//        playerVisualization.initialize();
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
