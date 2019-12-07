package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class ConditionActionComboBox extends ComboBox {
    private static final String CONDITION_ARGUMENTS = "resources.gae.ConditionArguments";
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 300;

    private ResourceBundle expectedArguments;

    public ConditionActionComboBox(){
        expectedArguments = ResourceBundle.getBundle(CONDITION_ARGUMENTS);
        this.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                openArgumentWindow();
            }
        });
    }

    public void openArgumentWindow(){
        Stage argumentStage = new Stage();
        VBox argumentVBox = new VBox();
        String condition = this.getValue().toString();
        String[] arguments = expectedArguments.getString(condition).split(",");
        for(String argument : arguments){
            argumentVBox.getChildren().add(new Label(argument));
        }
        BorderPane root = new BorderPane();
        root.setTop(argumentVBox);
        argumentStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        argumentStage.show();
    }
}
