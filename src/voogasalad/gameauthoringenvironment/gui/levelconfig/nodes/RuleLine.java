package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.ConditionActionReader;

import java.util.Map;

public class RuleLine extends HBox {
    ConditionActionReader conditionActionComboBox;

    public RuleLine(Map<String, Map<String, Map<String, String>>> allActiveObjectMapParam){
        super(10);
        conditionActionComboBox = new ConditionActionReader(allActiveObjectMapParam);
        this.getChildren().addAll(new Label("Condition: "), conditionActionComboBox.getConditions(), new Label("  --->   Action: "), conditionActionComboBox.getActions());
    }

}
