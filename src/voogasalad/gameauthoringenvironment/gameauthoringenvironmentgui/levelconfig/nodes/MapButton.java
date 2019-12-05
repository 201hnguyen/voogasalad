package voogasalad.gameauthoringenvironment.gameauthoringenvironmentgui.levelconfig.nodes;

import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gameauthoringenvironmentgui.mapconfig.MapConfig;

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
