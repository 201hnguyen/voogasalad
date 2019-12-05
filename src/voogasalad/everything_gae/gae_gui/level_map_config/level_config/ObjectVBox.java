package voogasalad.everything_gae.gae_gui.level_map_config.level_config;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ObjectVBox extends VBox {
    FlowPane createdObjects;

    public ObjectVBox(String type, int width, int height){
        super(10);
        Label createdLabel = new Label("Created " + type);
        createdLabel.setTextFill(Color.CHOCOLATE);
        createdObjects = new FlowPane();
        createdObjects.setAlignment(Pos.CENTER);
        this.setMaxHeight(height/5);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefWidth(width/3);
        this.setStyle("-fx-border-color: black;\n");
        this.getChildren().addAll(createdLabel, createdObjects);
    }

    public void addToObjectHBox(Node icon){
        createdObjects.getChildren().add(icon);
    }

}
