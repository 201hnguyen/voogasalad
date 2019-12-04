package voogasalad.everything_gae.gae_gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CreateObjectParameters {

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
    private List<Node> allNodes = new ArrayList<>();
    private List<String> fieldTypes= new ArrayList<>();
    private FieldTextReturnFactory fieldFactory = new FieldTextReturnFactory();
    private List<Label> labelList;
    private List<String> labelText;
    private List<String> labelValue;
    private AddToXML xmlObject;
    private VBox vBoxFromAccordion;
    //private static Map<String, Map<String,String>> sendToXML;



    public CreateObjectParameters(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam, VBox accordionVBox) throws ParserConfigurationException {
        labelList = new ArrayList<>();
        labelText = new ArrayList<>();
        labelValue = new ArrayList<>();
        //sendToXML = new HashMap<>();
        towerPreferencePage = new Stage();
        root = new BorderPane();
        testRoot = new BorderPane();
        xmlObject = new AddToXML();
        vBoxFromAccordion = accordionVBox;
        properties = propertiesParam;
        paramFieldType = paramFieldTypeParam;
        gameObjectName = gameObjectNameParam;
        storeAllFieldTypes();
        addInputFields();
    }

    private void addInputFields() {

        vBox = new VBox();
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
        Button addButton = new Button("Submit");
        addButton.setOnMouseClicked(event -> {
        allNodes
                .stream()
                .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

        SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
        String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
        addToAccordion(createObjectIcon(myGuiParameters.getMap(), myLabel),vBoxFromAccordion);
        });

        return addButton;
    }

    private Node createObjectFromString(String type){
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

    private void storeAllFieldTypes(){
        for(String key : paramFieldType.keySet()){
            String typesOfFields = paramFieldType.getString(key);
            if(!(fieldTypes.contains(typesOfFields))){
                fieldTypes.add(typesOfFields);
            }
        }
    }

    private Button createObjectIcon(Map myMap, String objectName){
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

    private void addToAccordion(Button myButton, VBox myVbox){
        myVbox.getChildren().add(myButton);
    }

//    private void addToSendToXMLMap(Map myMap){
//        int i = 0;
//        while(true){
//            String putInMap = String.join(",", gameObjectName, Integer.toString(i));
//            if(!(sendToXML.containsKey(putInMap))){
//                sendToXML.putIfAbsent(putInMap, myMap);
//                break;
//            }
//            i++;
//        }
//    }

}
