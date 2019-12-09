package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gui.mapconfig.MapConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapButton extends Button {
    private List<Integer> listOfActiveEnemies;

    public MapButton(int width, int height, Map<String, Map<String, String>> allActiveObjects){
        super("Click To Configure Map For Level");
        this.setPrefWidth(width);
        this.setPrefHeight(height/10);
        this.setOnMouseClicked(event -> {
            listOfActiveEnemies = getListOfActiveEnemies(allActiveObjects);
            MapConfig map = new MapConfig(this, listOfActiveEnemies);
        });
    }

    public void mapSubmitted(){
        this.setText("Map Successfully Configured!");
        this.setStyle("-fx-background-color: #00ff00");
    }

    private List<Integer> getListOfActiveEnemies(Map<String, Map<String, String>> allActiveObjects){
        List<Integer> returnList = new ArrayList<>();
        for(String key : allActiveObjects.keySet()){
            if (key.contains("Enemies")) {
                returnList.add(Integer.parseInt(key.split(",")[1]));
            }
        }
        return returnList;
    }

}
