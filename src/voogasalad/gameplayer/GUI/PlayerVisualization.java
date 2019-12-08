package voogasalad.gameplayer.GUI;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameplayer.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerVisualization extends BorderPane {

    private static final double SCENE_WIDTH = 1000;
    private static final double SCENE_HEIGHT = 800;
    private static final double PANEL_POSITION = 800;
    private static final double LAYOUT = 0;
    private static final String TITLE = "Player";

    private Scene scene;
    private Stage stage;
    private DisplayScreen displayScreen;
    private Timeline timeline;
    private BackgroundImage backgroundImage;
    private VBox panelBox;
    private AccordionCreator accordionCreator;
    private StatusBar statusBar;
    private ActionsProcessor uiActionsProcessor;
    private StopWatch myStopWatch;
    private Text myStopWatchDisplay;
    private Player myPlayer;

    public PlayerVisualization(Stage stage, Timeline timeline, ActionsProcessor uiActionsProcessor, Player player) {
        this.stage = stage;
        this.timeline = timeline;
        this.uiActionsProcessor = uiActionsProcessor;
        this.myPlayer = player;
        initialize();
    }

    public void update(List<Sprite> sprites, Map<String, Integer> gameInfoMap) {
        displayScreen.updateDisplayScreen(sprites);
        statusBar.updateDisplayedInfo(gameInfoMap);
        myStopWatchDisplay.setText(myStopWatch.getCurrentTime());
    }

    public void setNewLevel(List<Sprite> towers, List<Sprite> enemies, String backgroundImagePath, Map<String, Integer> gameInfoMap){
        myStopWatch = new StopWatch();
        statusBar.updateDisplayedInfo(gameInfoMap);
        displayScreen.updateDisplayScreen(new ArrayList<>());
        int i = 0;
        HashMap<Integer, Integer> idMap = new HashMap<>();
        for(Sprite tower : towers){
            idMap.put(i, tower.getPrototypeId());
            i++;
        }
        accordionCreator.updateAvailableTowersAndEnemies(towers, enemies, idMap);
        setBackgroundImage(backgroundImagePath);
    }

    private void initialize() {
        ButtonCreator buttonCreator = new ButtonCreator(new ButtonController(this));
        accordionCreator = new AccordionCreator();
        statusBar = new StatusBar();
        panelBox = new VBox(10);
        panelBox.getChildren().add(buttonCreator);
        panelBox.getChildren().add(accordionCreator);
        createStopWatchDisplay();
        statusBar.getChildren().add(myStopWatchDisplay);
        this.setRight(panelBox);
        this.setTop(statusBar);
        scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
        displayGameScreenAndAttachToAccordion();
        showStage();
    }

    private void displayGameScreenAndAttachToAccordion() {
        displayScreen = new DisplayScreen(uiActionsProcessor, myPlayer);
        displayScreen.setMinWidth(SCENE_WIDTH - (SCENE_WIDTH - PANEL_POSITION));
        displayScreen.setMinHeight(SCENE_HEIGHT - this.getTop().getLayoutY());
        accordionCreator.attachDisplayScreen(displayScreen);
    }

    private void showStage() {
        this.setCenter(displayScreen);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();

    }

    private void createStopWatchDisplay(){
        myStopWatchDisplay = new Text("\n 0 : 0");
        myStopWatchDisplay.setFont(new Font(20));
    }

    private void setBackgroundImage(String backgroundImagePath){
        backgroundImage = new BackgroundImage(new Image(backgroundImagePath), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(SCENE_WIDTH - (SCENE_WIDTH - PANEL_POSITION), SCENE_HEIGHT, false, false, false, false));
        displayScreen.setBackground(new Background(backgroundImage));
    }

    public void startButtonAction() {
        timeline.play();
        myStopWatch.startStopWatch();
    }

    public void pauseButtonAction() {
        timeline.stop();
        myStopWatch.pauseStopWatch();
    }
}