package voogasalad.everything_gae.gae_gui.TabConfig;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voogasalad.everything_gae.gae_gui.AddToXML;
import voogasalad.everything_gae.gae_gui.CreateObjectParameters;
import voogasalad.everything_gae.gae_gui.FieldTextReturnFactory;
import voogasalad.everything_gae.gae_gui.SaveGUIParameters;

import java.lang.reflect.InvocationTargetException;
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

        new CreateObjectParameters();


        //add node content to VBox
        configVBox.getChildren().addAll(
                configLabel);


        //add VBox to tab
        myTowerConfigTab.setContent(configVBox);

        return myTowerConfigTab;
    }

    // a help
    private void addInputFields(VBox vBox) {
        System.out.println("entered addInputFields method in TowerConfigTab"); //testing

        for (int j = 0; j < properties.length; j++) {
            Label label = new Label(properties[j]); //for SaveGuiParameters
            labelList.add(label);
            labelText.add(label.getText());
            vBox.getChildren().add(label);
            vBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }
        root.setCenter(vBox);
        root.setBottom(createSubmitButton());
        towerEditScene = new Scene(root, window_WIDTH, window_HEIGHT);
        towerPreferencePage.setScene(towerEditScene);
        towerPreferencePage.show();
    }

    private Button createSubmitButton(){
        System.out.println("Entered createSubmitButton in TowerConfigTab"); //testing
        Button addButton = new Button("Submit");
        addButton.setOnMouseClicked(event -> {
            allNodes
                    .stream()
                    .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

            SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
            String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
            addToVBox(createObjectIcon(myGuiParameters.getMap(), myLabel),vBoxFromAccordion);
        });

        return addButton;
    }

    // a helper method to implement reflection
    private Node createObjectFromString(String type){
        System.out.println("Entered createObjectFromString in TowerConfigTab"); //testing
        try{
            Class cls = Class.forName(type);
            Node myField = (Node) cls.getConstructor().newInstance();
            allNodes.add(myField);

            return myField;
        } catch (IllegalAccessException e) {
            throw new Error(e);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        } catch (InstantiationException e) {
            throw new Error(e);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        }
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
