package voogasalad;
import bus.Bus;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    public static final String TITLE = "Graphical Authoring Environment";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public Bus myBus;

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(setBusScene(primaryStage, root));
        primaryStage.show();
    }

    private Scene setBusScene(Stage currentStage, BorderPane root){
        myBus = new Bus(currentStage, root, WIDTH, HEIGHT);
        return myBus.getBusScene();
    }
    //javafx
}
