package voogasalad.gameplayer.GUI;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad.gameengine.engine.sprites.JavaFXSprite;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameplayer.DisplayScreen;

import java.util.List;

public class PlayerVisualization {

    private static final double SCENE_WIDTH = 1000;
    private static final double SCENE_HEIGHT = 630;
    private static final double PANEL_POSITION = 700;
    private static final double LAYOUT = 0;
    private static final String TITLE = "Player";

    private Scene scene;
    private Stage stage;
    private DisplayScreen displayScreen;
    private Pane root;

    public PlayerVisualization(Stage stage, List<Sprite> sprites) {
        this.stage = stage;
        initialize(sprites);
    }

    public void showStage(List<Sprite> sprites) {
        displayScreen = (DisplayScreen) displayScreen(sprites);
        root.getChildren().addAll(displayScreen);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
    }

    public void initialize(List<Sprite> sprites) {
        root = new Pane();
        ButtonCreator buttonCreator = new ButtonCreator();
        AccordionCreator accordionCreator = new AccordionCreator(sprites);
        VBox panelBox = new VBox();
        panelBox.getChildren().add(buttonCreator);
        panelBox.getChildren().add(accordionCreator);
        panelBox.setLayoutX(PANEL_POSITION);
        root.getChildren().addAll(panelBox);
        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.show();
    }

    private Pane displayScreen(List<Sprite> sprites) {
        DisplayScreen displayScreen = new DisplayScreen(sprites);
        displayScreen.setMinWidth(PANEL_POSITION);
        displayScreen.setMinHeight(SCENE_HEIGHT);
        displayScreen.setLayoutX(LAYOUT);
        displayScreen.setLayoutY(LAYOUT);
        displayScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return displayScreen;
    }
}