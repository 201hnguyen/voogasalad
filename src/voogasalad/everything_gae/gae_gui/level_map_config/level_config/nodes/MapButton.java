package voogasalad.everything_gae.gae_gui.level_map_config.level_config.nodes;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import voogasalad.everything_gae.gae_gui.level_map_config.map_config.MapConfig;

public class MapButton extends Button {

    public MapButton(int width, int height){
        super("Click To Configure Map For Level");
        this.setPrefWidth(width);
        this.setPrefHeight(height/10);
        this.setOnMouseClicked(event -> {
            MapConfig map = new MapConfig(this);
        });
    }

    public void mapSubmitted(){
        this.setText("Map Successfully Configured!");
        this.setStyle("-fx-background-color: #00ff00");
    }

}
