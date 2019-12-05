package voogasalad.everything_gae.gae_gui.level_map_config.level_config;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LevelConfigScene{
    private int width = 500;
    private int height = 500;

    public Scene getScene(BorderPane root){

        Label l = new Label("Created Towers");
        l.setAlignment(Pos.CENTER);

        Separator separator = new Separator(Orientation.HORIZONTAL);
        Separator separator2 = new Separator(Orientation.HORIZONTAL);
        Separator separator3 = new Separator(Orientation.HORIZONTAL);

        VBox createdTowers = new VBox(10);
        createdTowers.getChildren().addAll(l, separator, new FlowPane());
        createdTowers.setPrefWidth(width/3);
        createdTowers.setStyle("-fx-border-color: black;\n");
        VBox createdEnemies = new VBox(10);
        createdEnemies.getChildren().addAll(new Label("Created Enemies"), separator2, new FlowPane());
        createdEnemies.setPrefWidth(width/3);
        createdEnemies.setStyle("-fx-border-color: black;\n");
        VBox createdObstacles = new VBox(10);
        createdObstacles.getChildren().addAll(new Label("Created Obstacles"), separator3, new FlowPane());
        createdObstacles.setPrefWidth(width/3);
        createdObstacles.setStyle("-fx-border-color: black;\n");

        root.setLeft(createdTowers);
        root.setCenter(createdEnemies);
        root.setRight(createdObstacles);

//        VBox createdTowers = new VBox(10);
//        createdTowers.getChildren().addAll(new Label("Created Towers"), separator, new FlowPane());
//
//        VBox createdTowers1 = new VBox(10);
//        createdTowers1.getChildren().addAll(new Label("Created Towers"), separator, new FlowPane());
//
//        VBox createdTowers2 = new VBox(10);
//        createdTowers2.getChildren().addAll(new Label("Created Towers"), separator, new FlowPane());
//
//        HBox createdObjects = new HBox(20);
//        createdObjects.getChildren().addAll(createdTowers, createdTowers1, createdTowers2);
//        root.getChildren().add(createdObjects);




//        TableView tableView = new TableView();
//        TableColumn<String, String> col1 = new TableColumn<>("Created Towers");
//        TableColumn<String, String> col2 = new TableColumn<>("Created Enemies");
//        TableColumn<String, String> col3 = new TableColumn<>("Created Obstacles");
//        tableView.getColumns().addAll(col1, col2, col3);
//        root.setTop(tableView);
//        Separator separator = new Separator(Orientation.HORIZONTAL);
//        VBox createdTowers = new VBox();
//        createdTowers.setPrefWidth(height/3);
//        createdTowers.getChildren().addAll(new Label("Towers"),separator, new FlowPane());
//        VBox createdEnemies = new VBox();
//        createdEnemies.setPrefWidth(height/3);
//        createdEnemies.getChildren().addAll(new Label("Enemies"), separator, new FlowPane());
//        VBox createdObstacles = new VBox();
//        createdObstacles.setPrefWidth(height/3);
//        createdObstacles.getChildren().addAll(new Label("Obstacles"), separator, new FlowPane());
//        HBox createdObjects = new HBox();
//        createdObjects.getChildren().addAll(createdTowers, createdEnemies, createdObstacles);
//        createdObjects.setPrefHeight(50);
//        //createdObjects.getChildren().addAll(createdTowers, createdEnemies);
//        createdObjects.setStyle("-fx-border-color: black;\n");
//        root.setTop(createdObjects);
//        Label configLabel = new Label("LEVEL 1 CONFIGURATION");
////
////        Label towerLabel = new Label("Towers Created");
////        towerLabel.setTextAlignment(TextAlignment.CENTER);
////        Label enemyLabel = new Label("Enemies Created");
////        enemyLabel.setAlignment(Pos.CENTER);
////        Label obstacleLabel = new Label("Obstacles Created");
////        obstacleLabel.setAlignment(Pos.CENTER);
////
////        HBox createdObjectsHBox = new HBox();
////
////        Separator separator = new Separator(Orientation.HORIZONTAL);
////        FlowPane createdTowers = new FlowPane();
////        createdTowers.setStyle("-fx-border-color: black;\n");
////        createdTowers.getChildren().add(towerLabel);
////        FlowPane createdEnemies = new FlowPane();
////        createdEnemies.setStyle("-fx-border-color: black;\n");
////        createdEnemies.getChildren().add(towerLabel);
////        FlowPane createdObstacles = new FlowPane();
////        createdObstacles.setStyle("-fx-border-color: black;\n");
////        createdObstacles.getChildren().add(towerLabel);
////        HBox hBox = new VBox(configLabel, separator, createdTowers, createdEnemies, createdObstacles);
////
//////        VBox createdEnemiesVBox = new VBox();
//////        createdEnemiesVBox.setStyle("-fx-border-color: black;\n");
//////        createdEnemiesVBox.getChildren().add(enemyLabel);
//////        VBox createdObstaclesVBox = new VBox();
//////        createdObstaclesVBox.setStyle("-fx-border-color: black;\n");
//////        createdObstaclesVBox.getChildren().add(obstacleLabel);
////
//////        createdObjectsHBox.setHgrow(createdTowersVBox, Priority.ALWAYS);
//////        createdObjectsHBox.setHgrow(createdEnemiesVBox, Priority.ALWAYS);
//////        createdObjectsHBox.setHgrow(createdObstaclesVBox, Priority.ALWAYS);
////        createdObjectsHBox.getChildren().addAll(vBox);
////        root.setCenter(createdObjectsHBox);



        return new Scene(root, width, height);
    }

}
