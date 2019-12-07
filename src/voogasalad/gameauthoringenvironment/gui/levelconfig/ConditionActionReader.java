package voogasalad.gameauthoringenvironment.gui.levelconfig;

import javafx.scene.control.ComboBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.ConditionActionComboBox;

import java.util.ResourceBundle;

public class ConditionActionReader {
    private static final String CONDITION_ACTION_OPTIONS = "resources.gae.ConditionAction";
    private ResourceBundle conditionAction;
    private ConditionActionComboBox conditionComboBox;
    private ConditionActionComboBox actionComboBox;


    public ConditionActionReader(){
        conditionComboBox = new ConditionActionComboBox();
        actionComboBox = new ConditionActionComboBox();

        conditionAction = ResourceBundle.getBundle(CONDITION_ACTION_OPTIONS);
        conditionAction.getKeys().asIterator().forEachRemaining(key -> {
            if (key.contains("Condition")) {
                conditionComboBox.getItems().add(conditionAction.getString(key));
            }
            else{
                actionComboBox.getItems().add(conditionAction.getString(key));
            }
        });
    }

    public ComboBox getConditions(){
        return conditionComboBox;
    }

    public ComboBox getActions(){
        return actionComboBox;
    }

}
