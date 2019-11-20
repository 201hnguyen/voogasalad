package player;

import javafx.application.Application;
import javafx.stage.Stage;
import player.GUI.PlayerVisualization;

public class Tester extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        PlayerVisualization playerVisualization = new PlayerVisualization(stage);
        playerVisualization.initialize();


    }
}
