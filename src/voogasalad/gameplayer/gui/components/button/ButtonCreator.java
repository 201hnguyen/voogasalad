package voogasalad.gameplayer.gui.components.button;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import voogasalad.gameplayer.gui.components.button.ButtonController;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.ResourceBundle;

public class ButtonCreator extends HBox {
    private static final String RESOURCE_PATH = "resources.player.ButtonResource";

    private ResourceBundle resourceBundle;
    private ButtonController myButtonController;

    public ButtonCreator(ButtonController buttonController) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        myButtonController = buttonController;
        createButtons();
    }

    private void createButtons() {
        getChildren().clear();
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Button button = new Button(key);
            button.setOnAction(e -> callAction(key));
            button.setPrefHeight(getHeight());
            button.setMinWidth(100);
            getChildren().add(button);
        }
    }

    private void callAction(String key) {
        String methodName = resourceBundle.getString(key);
        try {
            Method m = myButtonController.getClass().getDeclaredMethod(methodName);
            m.invoke(myButtonController);
        } catch (Exception e) {
            // TODO: catch this error
        }
    }

}
