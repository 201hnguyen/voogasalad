package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.ConditionActionReader;

public class RuleLine extends HBox {
    ConditionActionReader conditionActionComboBox;

    public RuleLine(){
        super(10);
        conditionActionComboBox = new ConditionActionReader();
        this.getChildren().addAll(new Label("Condition: "), conditionActionComboBox.getConditions(), new Label("  --->   Action: "), conditionActionComboBox.getActions());
    }

}
