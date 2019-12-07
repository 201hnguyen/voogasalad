package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class ConditionActionComboBox extends ComboBox {
    public ConditionActionComboBox(){

        this.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println("Test");
            }
        });
    }

}
