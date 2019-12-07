package voogasalad.gameauthoringenvironment.gui.levelconfig;

import javafx.scene.control.ComboBox;
import java.util.ResourceBundle;

public class ConditionActionReader {
    private static final String CONDITION_ACTION_OPTIONS = "resources.gae.ConditionAction";
    private ResourceBundle conditionAction;
    private ComboBox conditionComboBox;
    private ComboBox actionComboBox;


    public ConditionActionReader(){
        conditionComboBox = new ComboBox();
        actionComboBox = new ComboBox();

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
