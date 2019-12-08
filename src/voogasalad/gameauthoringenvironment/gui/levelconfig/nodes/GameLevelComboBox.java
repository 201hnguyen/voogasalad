package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.scene.control.ComboBox;
        import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;

        import java.util.ArrayList;
        import java.util.List;

public class GameLevelComboBox extends ComboBox {
    private String current;
    private static List<Integer> allLevels;

    public GameLevelComboBox(int currentLevelParam){
        allLevels = new ArrayList<>();
        int currentLevel = currentLevelParam;
        //String currentLevel = "1";
        System.out.println("In here");
        this.setValue(currentLevel);
        this.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                updateLevelConfigFields(Integer.parseInt(newValue.toString()));
            }
        });
    }

    public void updateLevelConfigFields(int newValue){
        //Load New
        System.out.println("Test: " + newValue);
    }

    public void addToComboBox(List<Integer> allLevelsParam){
        //this.getItems().clear();
        allLevels = allLevelsParam;
        for(Integer level : allLevels){
            this.getItems().add(level);
        }
    }
}
