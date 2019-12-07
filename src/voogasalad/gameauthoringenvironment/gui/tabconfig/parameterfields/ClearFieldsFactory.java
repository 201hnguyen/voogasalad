package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ClearFieldsFactory {

    public void clearField(Object node){
        if(node instanceof javafx.scene.control.TextField){
            ((TextField) node).clear();
        }
        if(node instanceof ComboBox){
            ((ComboBox) node).getItems().clear();
        }

    }
}
