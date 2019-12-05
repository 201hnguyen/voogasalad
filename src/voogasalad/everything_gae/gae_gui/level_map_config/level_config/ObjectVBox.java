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
        Label createdLabel = createLabel(type, Color.CHOCOLATE);
        createFlowPane();
        createVBoxLayout(width, height);
        this.getChildren().addAll(createdLabel, createdObjects);
    }

    private void createVBoxLayout(int width, int height){
        this.setMaxHeight(height);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefWidth(width);
        this.setStyle("-fx-border-color: black;\n");
    }


    private void createFlowPane(){
        createdObjects = new FlowPane();
        createdObjects.setAlignment(Pos.CENTER);
    }

    private Label createLabel(String type, Paint color){
        Label createdLabel = new Label("Created " + type);
        createdLabel.setTextFill(color);
        return createdLabel;
    }

    public void addToObjectHBox(Node icon){
        createdObjects.getChildren().add(icon);
    }

}
