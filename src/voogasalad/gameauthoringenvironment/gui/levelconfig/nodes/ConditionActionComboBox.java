package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;

public class ConditionActionComboBox extends ComboBox {
    private static final String CONDITION_ARGUMENTS = "resources.gae.conditionaction.ConditionArguments";
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 300;

    private ResourceBundle expectedArguments;

    public ConditionActionComboBox(Map<String, Map<String, String>> allActiveObjectMap){
        expectedArguments = ResourceBundle.getBundle(CONDITION_ARGUMENTS);
        this.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    openArgumentWindow(allActiveObjectMap);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void openArgumentWindow(Map<String, Map<String, String>> allActiveObjectMap) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Stage argumentStage = new Stage();
        VBox argumentVBox = new VBox();
        String condition = this.getValue().toString();
        String[] arguments = expectedArguments.getString(condition).split(",");
        Label conditionLabel = new Label(condition);
        conditionLabel.setFont(Font.font("Verdana", 20));
        conditionLabel.setPadding(new Insets(0,0,20,0));
        argumentVBox.getChildren().add(conditionLabel);
        for(String argument : arguments){
            ArgumentHBox argumentHBox = new ArgumentHBox(argument, allActiveObjectMap, condition);
            argumentVBox.getChildren().add(argumentHBox);
        }
        BorderPane root = new BorderPane();
        root.setTop(argumentVBox);
        argumentStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        argumentStage.show();
        //
    }
}