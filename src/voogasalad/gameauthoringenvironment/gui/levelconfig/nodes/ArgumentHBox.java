package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Map;

public class ArgumentHBox extends HBox {

    public ArgumentHBox(String argument, Map<String, Map<String, Map<String, String>>> allActiveObjectMap){
        this.getChildren().add(new Label(argument + " -----> "));
        this.getChildren().add(appropriateComboBox(allActiveObjectMap));
    }


    public ComboBox appropriateComboBox(Map<String, Map<String, Map<String, String>>> allActiveObjectMap){
        // look at Resource file to determine which comboBox should be added
        return new ActiveObjectComboBox(allActiveObjectMap);
    }

}
