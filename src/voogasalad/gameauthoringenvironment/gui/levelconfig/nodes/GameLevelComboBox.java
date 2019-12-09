package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ObjectPreviewAndActive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameLevelComboBox extends ComboBox {
    private String current;
    private List<Integer> allLevels;
    private String currentLevel;
    private LevelConfigPane levelConfigPaneInstance;
    private List<ObjectPreviewAndActive> activeObjectObjects;
    private Map<Integer, Map<String, Map<String, String>>> activeObjectsForLevel;

    public GameLevelComboBox(LevelConfigPane levelConfigPaneInstanceParam){
        levelConfigPaneInstance = levelConfigPaneInstanceParam;
        allLevels = new ArrayList<>();
        activeObjectsForLevel = levelConfigPaneInstance.getActiveObjectsForLevel();
        activeObjectObjects = levelConfigPaneInstance.getActiveObjectObjects();
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

    private void updateLevelConfigFields(String newValue){
        System.out.println("Test: " + newValue);
        if(activeObjectsForLevel.containsKey(Integer.parseInt(newValue))){
            getAssociateObjectObject(activeObjectsForLevel.get(Integer.parseInt(newValue)), Integer.parseInt(newValue));
        }
    }

    public void addToComboBox(int previousLevel, int currentLevelParam){
        currentLevel = String.valueOf(currentLevelParam);
        allLevels.add(previousLevel);
        this.setValue(String.valueOf(currentLevel));
        this.getItems().add(previousLevel);

    }

    private void getAssociateObjectObject(Map<String, Map<String, String>> activeObjectMapForAppropriateLevel, int selectedLevel){
        String[] allActiveObjectsInLevel = Arrays.copyOf(activeObjectMapForAppropriateLevel.keySet().toArray(), activeObjectMapForAppropriateLevel.keySet().toArray().length, String[].class);
        for(ObjectPreviewAndActive objectObject : activeObjectObjects){
            if(Arrays.asList(allActiveObjectsInLevel).contains(objectObject.getName())){
                //System.out.println("Reactivate " + objectObject.getName());
                objectObject.reactivate();
                //makeActiveLevelInMap = selectedLevel
            }
        }
    }
}
