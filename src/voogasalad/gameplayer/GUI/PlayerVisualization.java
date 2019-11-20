package voogasalad.gameplayer.GUI;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad.gameengine.engine.sprites.Sprite;

import java.util.List;

public class PlayerVisualization extends Pane {

    private static final double SCENE_WIDTH = 1000;
    private static final double SCENE_HEIGHT = 630;
    private static final double PANEL_POSITION = 700;
    private static final double LAYOUT = 0;
    private static final String TITLE = "Player";

    private Scene scene;
    private Stage stage;
    private DisplayScreen displayScreen;
    private Timeline timeline;

    public PlayerVisualization(Stage stage, List<Sprite> sprites, Timeline timeline) {
        this.stage = stage;
        this.timeline = timeline;
        initialize(sprites);
        displayScreen(sprites);
        showStage();
    }

    public void showStage() {
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
    }

    public void update(List<Sprite> sprites) {
        this.getChildren().remove(displayScreen);
        displayScreen(sprites);
        this.getChildren().addAll(displayScreen);
    }

    public void initialize(List<Sprite> sprites) {
        ButtonCreator buttonCreator = new ButtonCreator(new ButtonController(this));
        AccordionCreator accordionCreator = new AccordionCreator(sprites);
        VBox panelBox = new VBox();
        panelBox.getChildren().add(buttonCreator);
        panelBox.getChildren().add(accordionCreator);
        panelBox.setLayoutX(PANEL_POSITION);
        this.getChildren().addAll(panelBox);
        scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
        stage.show();
    }

    public void startButtonAction() {
        timeline.play();
    }

    public void pauseButtonAction() {
        timeline.stop();
    }

    private void displayScreen(List<Sprite> sprites) {
        displayScreen = new DisplayScreen(sprites);
        displayScreen.setMinWidth(PANEL_POSITION);
        displayScreen.setMinHeight(SCENE_HEIGHT);
        displayScreen.setLayoutX(LAYOUT);
        displayScreen.setLayoutY(LAYOUT);
        displayScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}