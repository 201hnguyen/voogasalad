package voogasalad.everything_gae.gae_gui.level_map_config.level_config;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LevelConfigScene{
    private int width = 500;
    private int height = 500;

    public Scene getScene(BorderPane root){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        GridPane gridPane = new GridPane();
        HBox title = createTitleHBox();
        HBox allObjects = createAllObjectHBox();
        BorderPane rules = createRulesVBox();
        gridPane.addRow(0, title);
        gridPane.addRow(1, allObjects);
        gridPane.setVgap(30);
        gridPane.addRow(2, rules);
        scrollPane.setContent(gridPane);
        root.setTop(scrollPane);
        return new Scene(root, width, height);
    }


    public HBox createTitleHBox(){
        HBox titleHBox = new HBox();
        Label levelLabel = new Label("Level 1 Configuration");
        levelLabel.setFont(Font.font(30));
        titleHBox.getChildren().add(levelLabel);
        return titleHBox;
    }

    public HBox createAllObjectHBox(){
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
        allObjectHBox.setPrefHeight(height/5);
        allObjectHBox.getChildren().addAll(towersVBox, enemiesVBox, obstaclesVBox);
        return allObjectHBox;
    }

    public BorderPane createRulesVBox(){
        BorderPane borderPane = new BorderPane();
        Label rulesLabel = new Label("Rules: \n");
        rulesLabel.setFont(Font.font(20));
        VBox conditionAction = new VBox(10);
        conditionAction.getChildren().add(new RuleLine());
        Button addRuleLine = new Button("+");
        addRuleLine.setOnMouseClicked(event -> {
            conditionAction.getChildren().add(new RuleLine());
        });
        borderPane.setTop(rulesLabel);
        borderPane.setCenter(conditionAction);
        borderPane.setBottom(addRuleLine);
        return borderPane;
    }

}
