package gae_gui.gae_Tower;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.List;
import java.util.ResourceBundle;

public class GAETowerView {

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private GridPane root;
    private ResourceBundle myHelpContents;
    private Scene towerEditScene;
    private TextFlow myTextFlow;
    private Stage towerPreferencePage;
    private List<Double> towerAttributes;


   public GAETowerView(){
       towerPreferencePage = new Stage();
       root = new GridPane();
       addLabel();
       addInputField();

       towerEditScene = new Scene(root, window_WIDTH, window_HEIGHT);
       towerPreferencePage.setScene(towerEditScene);
       towerPreferencePage.show();

   }

   private void addInputField(){
       TextField attackPowerField = new TextField();
       attackPowerField.setId("AttackPower");
       TextField healthField = new TextField();
       attackPowerField.setId("AttackPower");
       TextField rangeField = new TextField();
       attackPowerField.setId("AttackPower");
       root.add(attackPowerField,2,3);
       root.add(healthField,2,4);
       root.add(rangeField,2,5);
   }

   private void createInputField(String fieldName){


   }

   private void addLabel(){
       Label title = new Label("New Turret Preferences");
       Label towerTypeLabel = new Label("Turret Type");
       Label towerAttackLabel = new Label("Attack Power");
       Label towerHealthLabel = new Label("Turret Health");
       Label towerRangeLabel = new Label("Turret Range");

       root.add(title,1,1);
       root.add(towerTypeLabel,1,2);
       root.add(towerAttackLabel,1,3);
       root.add(towerHealthLabel,1,4);
       root.add(towerRangeLabel,1,5);

   }


   /*
    private HBox createTopHBox() {
        HBox top = new HBox();
        top.setSpacing(20);
        Enumeration<String> dropDownLabels = dropDownResources.getKeys();

        while(dropDownLabels.hasMoreElements()) {
            LabeledComboBox toAdd = new LabeledComboBox(dropDownLabels.nextElement(), actionController, dropDownResources, dropDownActionResources);
            dropDowns.put(toAdd.getLabel(), toAdd);
            top.getChildren().add(toAdd);
        }

        top.setLayoutY(50);
        return top;
    }
*/
}
