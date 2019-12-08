package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;

import java.util.Map;

public class ActiveObjectComboBox extends ComboBox {

    private static Map<String, Map<String, String>> allActiveObjects;

    public ActiveObjectComboBox(){
        for(String type : allActiveObjects.keySet()){
                this.getItems().add(type);
        }
    }

    public ActiveObjectComboBox(Map<String, Map<String, String>> allActiveObjectMapParam){
        allActiveObjects = allActiveObjectMapParam;
    }

}
