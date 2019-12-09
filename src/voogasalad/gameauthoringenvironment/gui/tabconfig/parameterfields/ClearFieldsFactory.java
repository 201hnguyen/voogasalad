package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import voogasalad.gameauthoringenvironment.gui.utilconfig.*;

public class ClearFieldsFactory {

    public void clearField(Object node){
        if(node instanceof javafx.scene.control.TextField){
            ((TextField) node).clear();
        }
        if(node instanceof ComboBox){
            ((ComboBox) node).getItems().clear();
        }
        if(node instanceof ImageSpecSlider) {
            ((Slider) node).setValue(200);
        }
        if(node instanceof HealthCostSlider) {
            ((Slider) node).setValue(25);
        }
        if(node instanceof AttackRateSlider) {
            ((Slider) node).setValue(0.25);
        }
        if(node instanceof FileChooserButton) {
            ((FileChooserButton) node).clearImageString();
        }
        if(node instanceof PreviewImageButton) {
            ((PreviewImageButton) node).clearImageString();
        }
    }
}
