import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
//import slogo.view.commandpattern.DropDownCommand;

import java.util.ResourceBundle;

/**
 * Author: Chris Xu(cyx2)
 */
/*
public class LabeledComboBox extends VBox {
    private Label myLabel;
    private ComboBox myComboBox;
    private ResourceBundle dropDownResources;
    private ResourceBundle dropDownActionResources;

    public LabeledComboBox(String label, Controller target, ResourceBundle dropDown, ResourceBundle dropDownAction) {
        dropDownResources = dropDown;
        dropDownActionResources = dropDownAction;
        myLabel = new Label(label);
        myComboBox = createDropDown(label, target);
        getChildren().addAll(myLabel, myComboBox);
    }

    public String getLabel() {
        return myLabel.getText();
    }

    public String getDropDownValue() {
        return (String) myComboBox.getValue();
    }

    private ComboBox<String> createDropDown(String label, Controller target) {
        ComboBox<String> dropDown = new ComboBox<>();
        ObservableList<String> items = dropDown.getItems();
        items.addAll(dropDownResources.getString(label).split(","));
        dropDown.setOnAction(e -> {
                    DropDownCommand toExecute = new DropDownCommand(target, dropDownActionResources, label, dropDown.getValue());
                    toExecute.execute();
                });
        dropDown.getSelectionModel().select(0);
        return dropDown;
    }
}
*/