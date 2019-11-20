package voogasalad.gameplayer.GUI;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad.gameplayer.GUI.AccordionCreator;
import voogasalad.gameplayer.GUI.ButtonCreator;

public class PlayerVisualization {

    private static final double SCENE_WIDTH = 830;
    private static final double SCENE_HEIGHT = 630;
    private static final double PANEL_POSITION = 700;
    private static final double LAYOUT = 0;
    private static final String TITLE = "Player";

    private Scene scene;
    private Stage stage;

    public PlayerVisualization(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void startStage() {
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();
    }

    public void initialize() {
        Pane root = new Pane();
        ButtonCreator buttonCreator = new ButtonCreator();
        AccordionCreator accordionCreator = new AccordionCreator();
        VBox panelBox = new VBox();
        panelBox.getChildren().add(buttonCreator);
        panelBox.getChildren().add(accordionCreator);
        panelBox.setLayoutX(PANEL_POSITION);
        Pane displayScreen = displayScreen();
        root.getChildren().addAll(displayScreen, panelBox);
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage();
    }

    private Pane displayScreen() {
        Pane displayScreen = new Pane();
        displayScreen.setMinWidth(PANEL_POSITION);
        displayScreen.setMinHeight(SCENE_HEIGHT);
        displayScreen.setLayoutX(LAYOUT);
        displayScreen.setLayoutY(LAYOUT);
        displayScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return displayScreen;
    }
}