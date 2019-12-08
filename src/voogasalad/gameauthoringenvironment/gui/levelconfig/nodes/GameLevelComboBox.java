package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class GameLevelComboBox extends ComboBox {
    private String current;
    private List<Integer> allLevels;
    private String currentLevel;

    public GameLevelComboBox(){
        allLevels = new ArrayList<>();
        this.setValue("1");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ComboBox thisInstance = (ComboBox) event.getSource();
                String currentVal = thisInstance.getValue().toString();
                updateLevelConfigFields(currentVal);
            }
        });
    }

    public void updateLevelConfigFields(String newValue){
        System.out.println("Test: " + newValue);
    }

    public void addToComboBox(int previousLevel, int currentLevelParam){
        currentLevel = String.valueOf(currentLevelParam);
        allLevels.add(previousLevel);
        this.setValue(String.valueOf(currentLevel));
        this.getItems().add(previousLevel);

    }
}
