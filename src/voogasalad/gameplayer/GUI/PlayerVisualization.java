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
import voogasalad.gameengine.api.Engine;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameplayer.Player;

import java.util.*;

public class PlayerVisualization extends BorderPane {

    private static final String RESOURCE_PATH = "resources.player.ButtonResource";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private static final double SCENE_WIDTH = Double.parseDouble(resourceBundle.getString("SceneWidth"));
    private static final double SCENE_HEIGHT = Double.parseDouble(resourceBundle.getString("SceneHeight"));
    private static final double PANEL_POSITION = Double.parseDouble(resourceBundle.getString("RightPanelPosition"));
    private static final String TITLE = resourceBundle.getString("Title");
    private static final int STOPWATCH_FONT_SIZE = Integer.parseInt(resourceBundle.getString("StopwatchFontSize"));
    private static final String INITIAL_TIME = resourceBundle.getString("InitialTime");

    private Scene scene;
    private Stage stage;
    private DisplayScreen displayScreen;
    private BackgroundImage backgroundImage;
    private VBox panelBox;
    private AccordionCreator accordionCreator;
    private StatusBar statusBar;
    private SelectedTowerPane selectedTowerPane;
    private ActionsProcessor actionsProcessor;
    private StopWatch myStopWatch;
    private Text myStopWatchDisplay;
    private Player myPlayer;
    private boolean isRunning;
    private String currentTime;

    public PlayerVisualization(Stage stage, ActionsProcessor uiActionsProcessor, Player player) {
        this.stage = stage;
        this.actionsProcessor = uiActionsProcessor;
        this.myPlayer = player;
        this.isRunning = false;
        currentTime = INITIAL_TIME;
        initialize();
    }

    public void update(List<Sprite> sprites, Map<String, Integer> gameInfoMap) {
        displayScreen.updateDisplayScreen(sprites);
        statusBar.updateDisplayedInfo(gameInfoMap);
        if(isRunning) {
            currentTime = myStopWatch.getCurrentTime();
        }
        myStopWatchDisplay.setText(currentTime);
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
        selectedTowerPane = new SelectedTowerPane(actionsProcessor, myPlayer, this);
        panelBox = new VBox(10);
        panelBox.getChildren().addAll(buttonCreator,accordionCreator, selectedTowerPane);
        createStopWatchDisplay();
        statusBar.getChildren().add(myStopWatchDisplay);
        this.setRight(panelBox);
        this.setTop(statusBar);
        scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
        displayGameScreenAndAttachToAccordion();
        showStage();
    }

    private void displayGameScreenAndAttachToAccordion() {
        displayScreen = new DisplayScreen(actionsProcessor, myPlayer, selectedTowerPane, this);
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
        myStopWatchDisplay = new Text(INITIAL_TIME);
        myStopWatchDisplay.setFont(new Font(STOPWATCH_FONT_SIZE));
    }

    private void setBackgroundImage(String backgroundImagePath){
        backgroundImage = new BackgroundImage(new Image(backgroundImagePath), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(SCENE_WIDTH - (SCENE_WIDTH - PANEL_POSITION), SCENE_HEIGHT, false, false, false, false));
        displayScreen.setBackground(new Background(backgroundImage));
    }

    public void startButtonAction() {
        isRunning = true;
        myPlayer.startTimeLine();
        myStopWatch.startStopWatch();
    }

    public void pauseButtonAction() {
        isRunning = false;
        myPlayer.pauseTimeline();
        myStopWatch.pauseStopWatch();
    }
}