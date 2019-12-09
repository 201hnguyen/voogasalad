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
    private List<Map<String, Map<String, String>>> activeObjectsForLevel;
    private int selectedLevel;
    private int highestLevel;
    private boolean alreadyUpdated;

    public GameLevelComboBox(LevelConfigPane levelConfigPaneInstanceParam){
        levelConfigPaneInstance = levelConfigPaneInstanceParam;
        allLevels = new ArrayList<>();
        selectedLevel = 1;
        highestLevel = 1;
        this.setValue("1");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ComboBox thisInstance = (ComboBox) event.getSource();
                String selectedLevel = thisInstance.getValue().toString();
                if(! (selectedLevel.equals(String.valueOf(highestLevel)))){
                    updateLevelConfigFields(selectedLevel);
                }
            }
        });
    }

    private void updateLevelConfigFields(String selectedLevelParam){
        activeObjectsForLevel = levelConfigPaneInstance.getActiveObjectsForLevel(Integer.parseInt(selectedLevelParam));
        activeObjectObjects = levelConfigPaneInstance.getActiveObjectObjects();
        levelConfigPaneInstance.saveInfoForLevel();
        if(! (activeObjectsForLevel == null)){
            for(Map map : activeObjectsForLevel){
                getAssociateObjectObject(map);
            }
        }
        selectedLevel = Integer.parseInt(selectedLevelParam);
        this.setValue(selectedLevel);


    }

    public void addToComboBox(int previousLevel, int currentLevelParam){
        alreadyUpdated = false;
        highestLevel = currentLevelParam;
        //currentLevel = String.valueOf(currentLevelParam);
        selectedLevel = highestLevel;
        allLevels.add(previousLevel);
        this.setValue(String.valueOf(highestLevel));
        this.getItems().add(previousLevel);

    }

    private void getAssociateObjectObject(Map<String, Map<String, String>> activeObjectMapForAppropriateLevel){
        String[] allActiveObjectsInLevel = Arrays.copyOf(activeObjectMapForAppropriateLevel.keySet().toArray(), activeObjectMapForAppropriateLevel.keySet().toArray().length, String[].class);
        for(ObjectPreviewAndActive objectObject : activeObjectObjects){
            if(Arrays.asList(allActiveObjectsInLevel).contains(objectObject.getName())){
                objectObject.reactivate();
            }
        }
    }

    public int getSelectedLevel(){
        return selectedLevel;
    }
}
