package voogasalad;

import GamePlayer.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String XML = "src/Example_Resources/MockData.xml";

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Player player = new Player(primaryStage, XML);
        player.startGame();
    }
}
