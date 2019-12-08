package voogasalad.gameauthoringenvironment.gui.levelconfig;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.*;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ObjectPreviewAndActive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelConfigPane extends BorderPane{
    private int width = 500;
    private int height = 500;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private List<VBoxCreator> objectVBoxes;
    private int gameLevel;
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
    private String[] allObjectTypes;
    private List<Integer> allLevels;
    private GameLevelComboBox gameLevelComboBox;


    public LevelConfigPane(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam, Map<String, Map<String, String>> allActiveObjectMapParam,
                           List<ObjectPreviewAndActive> allActiveObjectObjectsParam, String[] allObjectTypesParam){
        allActiveObjectObjects = allActiveObjectObjectsParam;
        allLevels = new ArrayList<>();
        gameLevel = 1;
        saveActiveObjectsForLevel = new HashMap<>();
        allActiveObjects = allActiveObjectMapParam;
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        allObjectTypes = allObjectTypesParam;
        objectVBoxes = new ArrayList<>();
        gameLevelComboBox = new GameLevelComboBox();
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
        Label levelLabel = formatTitleLabel("Level ");
        gameLevelComboBox.setPrefHeight(height/10);
        Label configLabel = formatTitleLabel(" Configuration");
        //Label levelLabel = new Label("Level " + GameLevelComboBox + " Configuration");

        titleHBox.getChildren().addAll(levelLabel, gameLevelComboBox, configLabel);
        return titleHBox;
    }

    private Label formatTitleLabel(String title){
        Label label = new Label(title);
        label.setFont(Font.font(30));
        label.setPrefHeight(height/10);
        label.setMaxHeight(label.getPrefHeight());
        return label;
    }

    private HBox createAllObjectHBox(){
        HBox allObjectHBox = new HBox();
        int heightOfBox = 2*height/10;
        //int widthOfBox = width/3;
        int widthOfBox = width/(allObjectTypes.length);
        for(String objectType : allObjectTypes){
            VBoxCreator objectVBox = new VBoxCreator(objectType, widthOfBox, heightOfBox);
            objectVBoxes.add(objectVBox);
            allObjectHBox.getChildren().add(objectVBox);
        }
        allObjectHBox.setPrefHeight(heightOfBox);
        allObjectHBox.setMaxHeight(allObjectHBox.getPrefHeight());
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
        for(VBoxCreator objectVBox : objectVBoxes){
            objectVBox.addToObjectHBox(icon, objectType);
        }
    }

    private Button newLevelButton(){
        Button newLevel = new Button("Create New Level");
        newLevel.setOnMouseClicked(event -> {
            saveInfoForLevel();
            allLevels.add(gameLevel);
            gameLevelComboBox.addToComboBox(gameLevel, gameLevel+1);
            gameLevel++;
            updateLevelConfigPane();
        });
        return newLevel;
    }


    private void saveInfoForLevel(){
        saveAndClearActive();
    }

    private void saveAndClearActive(){ ;
        saveActiveObjectsForLevel.put(gameLevel, sendToLevelSave(allActiveObjects));
        for(ObjectPreviewAndActive object : allActiveObjectObjects){
            object.removeFromActive();
            //allActiveObjectObjects.remove(object);
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
