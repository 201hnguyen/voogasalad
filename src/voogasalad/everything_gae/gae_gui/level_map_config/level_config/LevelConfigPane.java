package voogasalad.everything_gae.gae_gui.level_map_config.level_config;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LevelConfigPane extends BorderPane{
    private int width = 500;
    private int height = 500;

    public LevelConfigPane(){
        setBorderPane();
    }

    public void setBorderPane(){
        GridPane gridPane = new GridPane();
        HBox title = createTitleHBox();
        HBox allObjects = createAllObjectHBox();
        ScrollPane rules = createRulesVBox();
        Label selectActiveLabel = createSelectActiveLabel();
        Button createMapButton = createMapButton();
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
        ObjectVBox towersVBox = new ObjectVBox("Towers", widthOfBox, heightOfBox);
        ObjectVBox enemiesVBox = new ObjectVBox("Enemies", widthOfBox, heightOfBox);
        ObjectVBox obstaclesVBox = new ObjectVBox("Obstacles", widthOfBox, heightOfBox);
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        towersVBox.addToObjectHBox(new Button("test"));
        enemiesVBox.addToObjectHBox(new Button("test"));
        enemiesVBox.addToObjectHBox(new Button("test"));
        enemiesVBox.addToObjectHBox(new Button("test"));
        obstaclesVBox.addToObjectHBox(new Button("test"));
        obstaclesVBox.addToObjectHBox(new Button("test"));
        obstaclesVBox.addToObjectHBox(new Button("test"));
        allObjectHBox.setPrefHeight(heightOfBox);
        allObjectHBox.setMaxHeight(allObjectHBox.getPrefHeight());
        allObjectHBox.getChildren().addAll(towersVBox, enemiesVBox, obstaclesVBox);
        return allObjectHBox;
    }

    private ScrollPane createRulesVBox(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(4*height/10);
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
        conditionAction.getChildren().addAll(new RuleLine(), new RuleLine());
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
            conditionAction.getChildren().add(new RuleLine());
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

    private Button createMapButton(){
        Button b = new Button("Click To Configure Map For Level");
        b.setPrefWidth(width);
        b.setPrefHeight(height/10);
        return b;
    }

    private HBox createSubmitNewLevelButtons(){
        HBox h = new HBox();
        h.setPrefWidth(width);
        h.setPrefHeight(height/10);
        Button newLevel = new Button("Create New Level");
        Button submit = new Button("Submit");
        newLevel.setPrefWidth(width/2);
        newLevel.setPrefHeight(h.getPrefHeight());
        submit.setPrefHeight(h.getPrefHeight());
        submit.setPrefWidth(width/2);
        h.getChildren().addAll(newLevel, submit);
        return h;
    }


}
