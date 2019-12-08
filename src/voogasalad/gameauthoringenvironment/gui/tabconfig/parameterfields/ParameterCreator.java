package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveGUIParameters;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.utils.SubmitButton;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ParameterCreator extends BorderPane{

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private static final String SUBMITBUTTONCLASS = new SubmitButton().getClass().toString().split("class ")[1];
    private BorderPane root;
    private ObjectPreviewAndActive objectSpecificRoot;
    private Stage newStage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox configVBox;
    private String gameObjectName;
    private List<Node> allNodes;
    private List<String> fieldTypes;
    private FieldTextReturnFactory fieldFactory;
    private List<Label> labelList;
    private List<String> labelText;
    private List<String> labelValue;
    private AddToXML xmlObject;
    private LevelConfigPane levelConfigPane;
    private Map<String, Map<String, String>> activeObjects;
    private ClearFieldsFactory clearFieldsFactory;
    //private static Map<String, Map<String,String>> sendToXML;


    public ParameterCreator(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam, LevelConfigPane levelConfigPaneParam) throws ParserConfigurationException {
        clearFieldsFactory = new ClearFieldsFactory();
        fieldFactory = new FieldTextReturnFactory();
        labelList = new ArrayList<>();
        labelText = new ArrayList<>();
        labelValue = new ArrayList<>();
        activeObjects = new HashMap<>();
        fieldTypes = new ArrayList<>();
        allNodes = new ArrayList<>();
        root = new BorderPane();
        xmlObject = new AddToXML();
        properties = propertiesParam;
        paramFieldType = paramFieldTypeParam;
        gameObjectName = gameObjectNameParam;
        levelConfigPane = levelConfigPaneParam;
        storeAllFieldTypes();
        addInputFields();
        this.setTop(configVBox);
    }

    private void addInputFields() {

        configVBox = new VBox();
        for (int j = 0; j < properties.length; j++) {
            Label label = new Label(properties[j]); //for SaveGuiParameters
            labelList.add(label);
            labelText.add(label.getText());
            configVBox.getChildren().add(label);
            configVBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }
    }

    public void createSubmitButton(){
            allNodes
                    .stream()
                    .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

            SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
            String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
            addToAppropriateField(gameObjectName, createObjectIcon(myGuiParameters.getMap(), myLabel));
    }

    private Node createObjectFromString(String type){
        try{
            if (type.equals(SUBMITBUTTONCLASS)) {
                SubmitButton myField = new SubmitButton(this);
                return myField;
            }
            if (type.equals()) {
                
            }
            else {
                Class cls = Class.forName(type);
                Node myField = (Node) cls.getConstructor().newInstance();
                allNodes.add(myField);
                return myField;
            }

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

    private Button createObjectIcon(Map<String, String> objectContentMap, String objectName){
        Button icon = new Button(objectName);
        icon.setOnMouseClicked(event -> {
            newStage = new Stage();
            objectSpecificRoot = new ObjectPreviewAndActive(objectName, objectContentMap, window_HEIGHT, window_WIDTH, newStage, activeObjects, icon);
            Scene newScene = new Scene(objectSpecificRoot, window_WIDTH, window_HEIGHT);
            newStage.setScene(newScene);
            newStage.show();
        });
        return icon;
    }

    private void addToAppropriateField(String gameObjectNameParam, Button icon){
        levelConfigPane.addIconToVBox(gameObjectNameParam, icon);
    }

    public void clearFields(){
        allNodes
                .stream()
                .forEach(node -> clearFieldsFactory.clearField(node));
    }


}

