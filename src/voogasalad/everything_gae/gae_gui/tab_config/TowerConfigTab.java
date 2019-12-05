package voogasalad.everything_gae.gae_gui.tab_config;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voogasalad.everything_gae.gae_gui.AddToXML;
import voogasalad.everything_gae.gae_gui.tab_config.object_param_creation.FieldTextReturnFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TowerConfigTab extends ConfigTabTemplate {

    private Tab myTowerConfigTab;
    private TabPane myTabPane;

    private List<Node> allNodes = new ArrayList<>();

    //from CreateObjectParameters
    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private BorderPane root;
    private BorderPane testRoot;
    private Scene towerEditScene;
    private Stage towerPreferencePage;
    private Stage newStage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox vBox;
    private String gameObjectName;
    private List<String> fieldTypes= new ArrayList<>();
    private FieldTextReturnFactory fieldFactory = new FieldTextReturnFactory();
    private List<Label> labelList;
    private List<String> labelText;
    private List<String> labelValue;
    private AddToXML xmlObject;
    private VBox vBoxFromAccordion;
    //private static Map<String, Map<String,String>> sendToXML;


    public TowerConfigTab() {
        myTowerConfigTab = addToTab();
    }

    /**
     * A getter method to retrieve the local variable myTowerConfigTab
     * @return
     */
    public Tab getTab() {
        return myTowerConfigTab;
    }

    // Creates a tab for the TowerConfig
    public Tab addToTab() {

        System.out.println("entered addToTab in TowerConfigTab"); //testing

        myTowerConfigTab = new Tab("Towers");

        //create node content for tab
        VBox configVBox = new VBox();
        Label configLabel = new Label("Configuration");

        //addInputFields(configVBox);

        //add node content to VBox
        configVBox.getChildren().addAll(
                configLabel);


        //add VBox to tab
        myTowerConfigTab.setContent(configVBox);

        return myTowerConfigTab;
    }



    private Button createObjectIcon(Map myMap, String objectName){
        System.out.printf("Entered CreateObjectIcon in TowerConfigTab");
        Button icon = new Button(objectName);
        icon.setOnMouseClicked(event -> {
            newStage = new Stage();
            testRoot.setCenter(new TextArea(myMap.toString()));
            Scene newScene = new Scene(testRoot, window_WIDTH, window_HEIGHT);
            newStage.setScene(newScene);
            newStage.show();
        });
        return icon;
    }

    private void addToVBox(Button button, VBox vBox){
        System.out.println("Entered addToVBox in TowerConfigTab");
        vBox.getChildren().add(button);
    }




}
