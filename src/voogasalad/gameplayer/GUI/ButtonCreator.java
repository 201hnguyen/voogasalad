package voogasalad.gameplayer.GUI;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.ResourceBundle;

public class ButtonCreator extends HBox {
    private static final double Y_LAYOUT = 430;
    private static final double X_LAYOUT = 20;
    private static final String RESOURCE_PATH = "resources.player.ButtonResource";

    private ResourceBundle resourceBundle;
    private ButtonController myButtonController;

    public ButtonCreator(ButtonController buttonController) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        myButtonController = buttonController;
        GridPane buttons = new GridPane();
        createButtons();
        setLayoutY(Y_LAYOUT);
        setLayoutX(X_LAYOUT);
    }

    private void createButtons() {
        getChildren().clear();
        for(String key : Collections.list(resourceBundle.getKeys())) {
            Button button = new Button(key);
            button.setOnAction(e -> callAction(key));
            button.setPrefHeight(getHeight());
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
