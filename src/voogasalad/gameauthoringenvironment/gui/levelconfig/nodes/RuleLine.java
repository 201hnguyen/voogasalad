package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RuleLine extends HBox {
    ConditionActionComboBox conditionActionComboBox;

    public RuleLine(){
        super(10);
        conditionActionComboBox = new ConditionActionComboBox();
        this.getChildren().addAll(new Label("Condition: "), conditionActionComboBox.getConditions(), new Label("  --->   Action: "), conditionActionComboBox.getActions());
    }

}
