package voogasalad.gameplayer.GUI;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerVisualization extends BorderPane {

    private static final double SCENE_WIDTH = 1000;
    private static final double SCENE_HEIGHT = 800;
    private static final double PANEL_POSITION = 800;
    private static final double PANEL_SPACING = 10;
    private static final double TIME_SIZE = 20;
    private static final double SHADOW_COLOR = 0.3f;
    private static final double SHADOW_ySET= 3.0f;
    private static final String BACKtoGAE = "Return to GAE";
    private static final String TITLE = "Player";
    private static final String INSTRUCTIONS = " Instructions: Drag and drop \n towers onto display screen.";
    private static final String TIMER_INFO = "Elapsed Time:"+ "\n 0 : 0";

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
    private boolean isGameRunning;
    private String currentTime;

    public PlayerVisualization(Stage stage, ActionsProcessor uiActionsProcessor, Player player) {
        this.stage = stage;
        this.actionsProcessor = uiActionsProcessor;
        this.myPlayer = player;
        this.isGameRunning = false;
        currentTime = TIMER_INFO;
        initialize();
    }

    public void update(List<Sprite> sprites, Map<String, Integer> gameInfoMap) {
        displayScreen.updateDisplayScreen(sprites);
        statusBar.updateDisplayedInfo(gameInfoMap);
        if(isGameRunning) {
            currentTime = myStopWatch.getCurrentTime();
            myStopWatchDisplay.setText(currentTime);
        }
        else{
            myStopWatchDisplay.setText(currentTime);
        }
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
        panelBox = new VBox(PANEL_SPACING);
        panelBox.getChildren().addAll(buttonCreator, showInstructions(), accordionCreator, selectedTowerPane, backToGAE());
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

    private VBox backToGAE() {
        VBox buttonHolder = new VBox();
        Button button = new Button(BACKtoGAE);
        buttonHolder.getChildren().add(button);
        buttonHolder.setAlignment(Pos.CENTER);
        return buttonHolder;
    }

    private Text showInstructions() {
        DropShadow shadow = getDropShadow();
        Text instructions = new Text();
        instructions.setText(INSTRUCTIONS);
        instructions.setFill(Color.BLACK);
        instructions.setEffect(shadow);
        return instructions;
    }


    private void createStopWatchDisplay(){
        DropShadow shadow = getDropShadow();
        myStopWatchDisplay = new Text(TIMER_INFO);
        myStopWatchDisplay.setEffect(shadow);
        myStopWatchDisplay.setFont(new Font(TIME_SIZE));
    }

    private DropShadow getDropShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(SHADOW_ySET);
        shadow.setColor(Color.color(SHADOW_COLOR, SHADOW_COLOR, SHADOW_COLOR));
        return shadow;
    }

    private void setBackgroundImage(String backgroundImagePath){
        backgroundImage = new BackgroundImage(new Image(backgroundImagePath), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(SCENE_WIDTH - (SCENE_WIDTH - PANEL_POSITION), SCENE_HEIGHT, false, false, false, false));
        displayScreen.setBackground(new Background(backgroundImage));
    }

    public void startButtonAction() {
        isGameRunning = true;
        myPlayer.startTimeLine();
        myStopWatch.startStopWatch();
    }

    public void pauseButtonAction() {
        isGameRunning = false;
        myPlayer.pauseTimeline();
        myStopWatch.pauseStopWatch();
    }
}