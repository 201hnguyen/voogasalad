package voogasalad.everything_gae.gae_gui.tab_config.object_param_creation;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voogasalad.everything_gae.gae_gui.AddToXML;
import voogasalad.everything_gae.gae_gui.SaveGUIParameters;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CreateObjectParams extends BorderPane{

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private BorderPane root;
    private BorderPane testRoot;
    private Scene towerEditScene;
    private Stage towerPreferencePage;
    private Stage newStage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox configVBox;
    private String gameObjectName;
    private List<Node> allNodes = new ArrayList<>();
    private List<String> fieldTypes= new ArrayList<>();
    private FieldTextReturnFactory fieldFactory = new FieldTextReturnFactory();
    private List<Label> labelList;
    private List<String> labelText;
    private List<String> labelValue;
    private AddToXML xmlObject;
    //private static Map<String, Map<String,String>> sendToXML;



    public CreateObjectParams(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam) throws ParserConfigurationException {
        labelList = new ArrayList<>();
        labelText = new ArrayList<>();
        labelValue = new ArrayList<>();
        towerPreferencePage = new Stage();
        root = new BorderPane();
        testRoot = new BorderPane();
        xmlObject = new AddToXML();
        properties = propertiesParam;
        paramFieldType = paramFieldTypeParam;
        gameObjectName = gameObjectNameParam;
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

    private Button createSubmitButton(){
        Button addButton = new Button("Submit");
        addButton.setOnMouseClicked(event -> {
            allNodes
                    .stream()
                    .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

            SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
            String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
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


}

