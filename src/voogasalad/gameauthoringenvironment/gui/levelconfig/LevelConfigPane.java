package voogasalad.gameauthoringenvironment.gui.levelconfig;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.MapButton;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.VBoxCreator;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.RuleLine;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.SubmitButton;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ObjectPreviewAndActive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelConfigPane extends BorderPane{
    private int width = 500;
    private int height = 500;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private VBoxCreator towersVBox;
    private VBoxCreator enemiesVBox;
    private VBoxCreator obstaclesVBox;
    private int gameLevel;
//    private Map<String, Map<String, Map<String, String>>> allActiveObjectMap;
//    private Map<String, Map<String, Map<String, String>>> activeObjectsInLevelTemp;
    private Map<String, Map<String, String>> allActiveObjects;
    private HBox title;
    private GridPane gridPane;
    private HBox allObjects;
    private ScrollPane rules;
    private Label selectActiveLabel;
    private Button createMapButton;
    private HBox createSubmitNewLevelButtons;
    private Map<Integer, Map<String, Map<String, String>>> saveActiveObjectsForLevel;
    private List<ObjectPreviewAndActive> allActiveObjectObjects;


    public LevelConfigPane(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam, Map<String, Map<String, String>> allActiveObjectMapParam,
                           List<ObjectPreviewAndActive> allActiveObjectObjectsParam){
        allActiveObjectObjects = allActiveObjectObjectsParam;
        gameLevel = 1;
        saveActiveObjectsForLevel = new HashMap<>();
        allActiveObjects = allActiveObjectMapParam;
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        setBorderPane();
    }

    private void setBorderPane(){
        title = createTitleHBox();
        allObjects = createAllObjectHBox();
        rules = createRulesVBox();
        selectActiveLabel = createSelectActiveLabel();
        createMapButton = new MapButton(width, height);
        createSubmitNewLevelButtons = createSubmitNewLevelButtons();
        addToGridPane();
    }

    private void addToGridPane(){
        gridPane = new GridPane();
        gridPane.addRow(0, title);
        gridPane.addRow(1, allObjects);
        gridPane.addRow(2, selectActiveLabel);
        gridPane.addRow(3, createMapButton);
        gridPane.addRow(4, rules);
        gridPane.addRow(5, createSubmitNewLevelButtons);
        this.setTop(gridPane);
    }


    private HBox createTitleHBox(){
        HBox titleHBox = new HBox();
        Label levelLabel = new Label("Level " + gameLevel + " Configuration");
        levelLabel.setFont(Font.font(30));
        levelLabel.setPrefHeight(height/10);
        levelLabel.setMaxHeight(levelLabel.getPrefHeight());
        titleHBox.getChildren().add(levelLabel);
        return titleHBox;
    }

    private HBox createAllObjectHBox(){
        HBox allObjectHBox = new HBox();
        int heightOfBox = 2*height/10;
        int widthOfBox = width/3;
        towersVBox = new VBoxCreator("Towers", widthOfBox, heightOfBox);
        enemiesVBox = new VBoxCreator("Enemies", widthOfBox, heightOfBox);
        obstaclesVBox = new VBoxCreator("Obstacles", widthOfBox, heightOfBox);
        allObjectHBox.setPrefHeight(heightOfBox);
        allObjectHBox.setMaxHeight(allObjectHBox.getPrefHeight());
        allObjectHBox.getChildren().addAll(towersVBox, enemiesVBox, obstaclesVBox);
        return allObjectHBox;
    }

    private ScrollPane createRulesVBox(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(3*height/10);
        scrollPane.setMaxHeight(scrollPane.getPrefHeight());
        scrollPane.setFitToHeight(true);
        BorderPane borderPane = new BorderPane();
        Label rulesLabel = createRulesLabel();
        VBox conditionAction = createConditionActionVBox();
        Button addRuleLine = createAddRuleLineButton(conditionAction);
        borderPane.setTop(rulesLabel);
        borderPane.setCenter(conditionAction);
        borderPane.setBottom(addRuleLine);
        scrollPane.setContent(borderPane);
        return scrollPane;
    }


    private VBox createConditionActionVBox(){
        VBox conditionAction = new VBox(10);
        conditionAction.getChildren().addAll(new RuleLine(allActiveObjects), new RuleLine(allActiveObjects));
        return conditionAction;
    }

    private Label createRulesLabel(){
        Label rulesLabel = new Label("Rules:");
        rulesLabel.setFont(Font.font(20));
        return rulesLabel;
    }

    private Button createAddRuleLineButton(VBox conditionAction){
        Button addRuleLine = new Button("+");
        addRuleLine.setOnMouseClicked(event -> {
            conditionAction.getChildren().add(new RuleLine(allActiveObjects));
        });
        return addRuleLine;
    }

    private Label createSelectActiveLabel(){
        Label label = new Label("Please Select All Active Towers, Enemies, and Obstacles");
        label.setFont(Font.font("Verdana"));
        label.setPrefHeight(height/10);
        label.setPrefWidth(width);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        return label;
    }


    private HBox createSubmitNewLevelButtons(){
        HBox h = new HBox();
        h.setPrefWidth(width);
        h.setPrefHeight(height/10);
        Button newLevel = newLevelButton();
        SubmitButton submit = new SubmitButton(createdXML, sendToXML, busInstance);
        newLevel.setPrefWidth(width/2);
        newLevel.setPrefHeight(h.getPrefHeight());
        submit.setPrefHeight(h.getPrefHeight());
        submit.setPrefWidth(width/2);
        h.getChildren().addAll(newLevel, submit);
        return h;
    }

    public void addIconToVBox(String objectType, Button icon){
        if(objectType.equals("Towers")){
            towersVBox.addToObjectHBox(icon);
        }
        if(objectType.equals("Enemies")){
            enemiesVBox.addToObjectHBox(icon);
        }
        if(objectType.equals("Obstacles")){
            obstaclesVBox.addToObjectHBox(icon);
        }

    }

    private Button newLevelButton(){
        Button newLevel = new Button("Create New Level");
        newLevel.setOnMouseClicked(event -> {
            saveInfoForLevel();
            gameLevel++;
            //Save in map linking level to all active objects
            updateLevelConfigPane();
        });
        return newLevel;
    }


    private void saveInfoForLevel(){
        saveAndClearActive();
    }

    private void saveAndClearActive(){
        //activeObjectsInLevelTemp = new HashMap<>(allActiveObjectMap);
        saveActiveObjectsForLevel.put(gameLevel, sendToLevelSave(allActiveObjects));
        //allActiveObjectMap = new HashMap<>();
        for(ObjectPreviewAndActive object : allActiveObjectObjects){
            object.removeFromActive();
        }
        System.out.println(saveActiveObjectsForLevel);
    }

    private void updateLevelConfigPane(){
        title = createTitleHBox();
        rules = createRulesVBox();
        createMapButton = new MapButton(width, height);
        addToGridPane();
    }

    private Map<String, Map<String, String>> sendToLevelSave(Map<String, Map<String, String>> activeObjects){
        Map<String, Map<String, String>> copyMap = new HashMap<>();
        for(String key : activeObjects.keySet()){
            copyMap.put(key, activeObjects.get(key));
        }
        return copyMap;
    }

}
