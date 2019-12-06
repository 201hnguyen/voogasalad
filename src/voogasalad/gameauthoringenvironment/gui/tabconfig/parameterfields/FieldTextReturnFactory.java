package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class FieldTextReturnFactory {

    public String getAppropriateText(Object node){
        if(node instanceof javafx.scene.control.TextField){
            return ((TextField) node).getText();
        }
        if(node instanceof ComboBox){
            return (String) ((ComboBox) node).getValue();
        }

//        }
        return "Field Type not recognized";
    }
}
