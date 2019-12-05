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
        VBox globalVBox = new VBox(10);
        HBox title = createTitleHBox();
        HBox allObjects = createAllObjectHBox();
        globalVBox.getChildren().addAll(title, allObjects);
        root.setTop(globalVBox);
        return new Scene(root, width, height);
    }


    public HBox createTitleHBox(){
        HBox titleHBox = new HBox();
        Label levelLabel = new Label("Level 1 Configuration");
        titleHBox.getChildren().add(levelLabel);
        return titleHBox;
    }

    public HBox createAllObjectHBox(){
        HBox allObjectHBox = new HBox();
        allObjectHBox.getChildren().addAll(new ObjectVBox("Towers", width, height), new ObjectVBox("Enemies", width, height), new ObjectVBox("Obstacles", width, height));
        return allObjectHBox;
    }

}
