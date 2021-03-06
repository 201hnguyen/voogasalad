package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ResourceBundle;

/**
 * node closs extending combobox for movement strategy
 * author. Xiaoyang
 */
public class MovementStrategyComboBox extends ComboBox {
    private String strategyResourcePath = "resources.gae.tabcreation.comboboxoptions.Strategy";
    private ResourceBundle comboRB;
    private static final String attackString = "MovementStrategy";

    public MovementStrategyComboBox () {
        comboRB = ResourceBundle.getBundle(strategyResourcePath);
        String[] items = comboRB.getString(attackString).split(",");
        ObservableList<String> choices= FXCollections.observableArrayList(items);

        this.setItems(choices);
    }
}
