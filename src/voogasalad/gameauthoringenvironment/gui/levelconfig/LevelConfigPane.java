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
    private Map<String, Map<String, Map<String, String>>> allActiveObjectMap;


    public LevelConfigPane(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam, Map<String, Map<String, Map<String, String>>> allActiveObjectMapParam){
        allActiveObjectMap = allActiveObjectMapParam;
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        setBorderPane();
    }

    public void setBorderPane(){
        GridPane gridPane = new GridPane();
        HBox title = createTitleHBox();
        HBox allObjects = createAllObjectHBox();
        ScrollPane rules = createRulesVBox();
        Label selectActiveLabel = createSelectActiveLabel();
        Button createMapButton = new MapButton(width, height);
        HBox createSubmitNewLevelButtons = createSubmitNewLevelButtons();
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
        Label levelLabel = new Label("Level 1 Configuration");
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
        conditionAction.getChildren().addAll(new RuleLine(allActiveObjectMap), new RuleLine(allActiveObjectMap));
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
            conditionAction.getChildren().add(new RuleLine(allActiveObjectMap));
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
        Button newLevel = new Button("Create New Level");
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


}
