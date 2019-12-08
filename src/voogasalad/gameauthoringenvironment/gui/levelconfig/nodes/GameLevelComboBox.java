package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.scene.control.ComboBox;
        import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;

        import java.util.ArrayList;
        import java.util.List;

public class GameLevelComboBox extends ComboBox {
    private String current;
    private List<Integer> allLevels;

    public GameLevelComboBox(){
        allLevels = new ArrayList<>();
        this.setValue("1");
//        this.valueProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                updateLevelConfigFields(Integer.parseInt(newValue.toString()));
//            }
//        });
    }

    public void updateLevelConfigFields(int newValue){
        //Load New
        System.out.println("Test: " + newValue);
    }

    public void addToComboBox(int previousLevel, int currentLevel){
        allLevels.add(previousLevel);
        this.setValue(String.valueOf(currentLevel));
        this.getItems().add(previousLevel);

    }
}
