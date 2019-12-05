package voogasalad.gameplayer.GUI;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad.gameengine.executors.sprites.Sprite;

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
    private BackgroundImage backgroundImage;

    public PlayerVisualization(Stage stage, List<Sprite> sprites, Timeline timeline, String backgroundImagePath) {
        this.stage = stage;
        this.timeline = timeline;
        this.backgroundImage = new BackgroundImage(new Image(backgroundImagePath), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(SCENE_WIDTH, SCENE_HEIGHT, false, false, false, true));
        displayScreen(sprites);
        initialize(sprites);
        showStage();
    }

    public void showStage() {
        this.getChildren().addAll(displayScreen);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();

    }

    public void update(List<Sprite> sprites) {
        this.getChildren().removeAll(displayScreen);
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
        System.out.println("hello");
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
        displayScreen.setBackground(new Background(backgroundImage));
    }
}