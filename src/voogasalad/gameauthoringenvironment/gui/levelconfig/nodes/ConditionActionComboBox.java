package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;
import java.util.ResourceBundle;

public class ConditionActionComboBox {
    private static final String CONDITION_ACTION_OPTIONS = "resources.gae.ConditionAction";
    private ResourceBundle conditionAction;
    private ComboBox conditionComboBox;
    private ComboBox actionComboBox;


    public ConditionActionComboBox(){
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
