package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import voogasalad.gameauthoringenvironment.gui.utilconfig.FileChooserButton;
import voogasalad.gameauthoringenvironment.gui.utilconfig.PreviewImageButton;


public class FieldTextReturnFactory {

    public String getAppropriateText(Object node){
        if(node instanceof javafx.scene.control.TextField){
            return ((TextField) node).getText();
        }
        if(node instanceof ComboBox){
            return (String) ((ComboBox) node).getValue();
        }
        if(node instanceof FileChooserButton) {
            return ((FileChooserButton) node).getImageString();
        }
        if(node instanceof PreviewImageButton) {
            return ((PreviewImageButton) node).getImageString();
        }
        return "Field Type not recognized";
    }
}
