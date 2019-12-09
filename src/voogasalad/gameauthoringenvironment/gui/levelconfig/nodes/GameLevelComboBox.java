//package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;
//
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.scene.control.ComboBox;
//
//public class GameLevelComboBox extends ComboBox {
//    public int selectedLevel;
//
//    public GameLevelComboBox(){
//        selectedLevel = 0;
//        this.valueProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                selectedLevel = this.getValue().toString();
//            }
//        });
//    }
//
//    public void updateLevelConfigFields(){}
//}
