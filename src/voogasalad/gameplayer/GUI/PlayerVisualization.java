package voogasalad.gameplayer.GUI;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.ArrayList;
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
    private VBox panelBox;
    private AccordionCreator accordionCreator;

    public PlayerVisualization(Stage stage, Timeline timeline) {
        this.stage = stage;
        this.timeline = timeline;
        initialize();
    }

    public void update(List<Sprite> sprites) {
        displayScreen.updateDisplayScreen(sprites);
    }

    public void setNewLevel(List<Sprite> towers, List<Sprite> enemies, String backgroundImagePath){
        displayScreen.updateDisplayScreen(new ArrayList<>());
        accordionCreator.updateAvailableTowersAndEnemies(towers, enemies);
        setBackgroundImage(backgroundImagePath);
    }

    private void initialize() {
        ButtonCreator buttonCreator = new ButtonCreator(new ButtonController(this));
        accordionCreator = new AccordionCreator();
        panelBox = new VBox();
        panelBox.getChildren().add(buttonCreator);
        panelBox.getChildren().add(accordionCreator);
        panelBox.setLayoutX(PANEL_POSITION);
        this.getChildren().addAll(panelBox);
        scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
        displayGameScreen();
        showStage();
    }

    private void showStage() {
        this.getChildren().addAll(displayScreen);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();

    }

    private void displayGameScreen() {
        displayScreen = new DisplayScreen();
        displayScreen.setMinWidth(PANEL_POSITION);
        displayScreen.setMinHeight(SCENE_HEIGHT);
        displayScreen.setLayoutX(LAYOUT);
        displayScreen.setLayoutY(LAYOUT);
    }

    private void setBackgroundImage(String backgroundImagePath){
        backgroundImage = new BackgroundImage(new Image(backgroundImagePath), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(SCENE_WIDTH - panelBox.getMaxWidth(), SCENE_HEIGHT, false, false, true, true));
        displayScreen.setBackground(new Background(backgroundImage));
    }

    public void startButtonAction() {
        timeline.play();
    }

    public void pauseButtonAction() {
        timeline.stop();
    }
}