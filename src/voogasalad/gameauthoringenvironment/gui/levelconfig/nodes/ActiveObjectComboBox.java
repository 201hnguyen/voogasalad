package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;

import java.util.Map;

public class ActiveObjectComboBox extends ComboBox {

    public ActiveObjectComboBox(Map<String, Map<String, Map<String, String>>> allActiveObjectMap){
        for(String type : allActiveObjectMap.keySet()){
            for(String name : allActiveObjectMap.get(type).keySet()){
                this.getItems().add(name);
            }
        }
    }

}
