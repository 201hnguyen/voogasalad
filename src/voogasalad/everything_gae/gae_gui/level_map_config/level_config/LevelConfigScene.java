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

public class LevelConfigScene{
    private int width = 500;
    private int height = 500;

    public Scene getScene(BorderPane root){

        GridPane gridPane = new GridPane();
        HBox title = createTitleHBox();
        HBox allObjects = createAllObjectHBox();
        ScrollPane rules = createRulesVBox();
        Label selectActiveLabel = createSelectActiveLabel();
        gridPane.addRow(0, title);
        gridPane.addRow(1, allObjects);
        gridPane.addRow(2, selectActiveLabel);
        gridPane.addRow(3, rules);
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        gridPane.setVgrow(scrollPane, Priority.ALWAYS);
        root.setTop(scrollPane);
        return new Scene(root, width, height);
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
        ObjectVBox towersVBox = new ObjectVBox("Towers", width, height);
        ObjectVBox enemiesVBox = new ObjectVBox("Enemies", width, height);
        ObjectVBox obstaclesVBox = new ObjectVBox("Obstacles", width, height);
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        towersVBox.addToObjectHBox(new Button("test"));
//        enemiesVBox.addToObjectHBox(new Button("test"));
//        enemiesVBox.addToObjectHBox(new Button("test"));
//        enemiesVBox.addToObjectHBox(new Button("test"));
//        obstaclesVBox.addToObjectHBox(new Button("test"));
//        obstaclesVBox.addToObjectHBox(new Button("test"));
//        obstaclesVBox.addToObjectHBox(new Button("test"));
        allObjectHBox.setPrefHeight(3*height/10);
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


}
