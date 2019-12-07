package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Map;

public class ArgumentHBox extends HBox {

    public ArgumentHBox(String argument, Map<String, Map<String, Map<String, String>>> allActiveObjectMap){
        this.getChildren().add(new Label(argument + " -----> "));
        this.getChildren().add(getActiveObjectComboBox(allActiveObjectMap));
    }



    private ComboBox getActiveObjectComboBox(Map<String, Map<String, Map<String, String>>> allActiveObjectMap){
        ComboBox activeObjects = new ComboBox();
        for(String type : allActiveObjectMap.keySet()){
            for(String name : allActiveObjectMap.get(type).keySet()){
                activeObjects.getItems().add(name);
            }
        }
        return activeObjects;
    }
}
