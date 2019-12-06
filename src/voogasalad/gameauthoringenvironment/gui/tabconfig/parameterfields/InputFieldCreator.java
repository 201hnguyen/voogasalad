package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.layout.*;
import javafx.stage.Stage;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveInputParameters;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 *
 */
public class InputFieldCreator extends BorderPane{

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private static final String TEST_IMAGE = "voogasalad_worksdonttouch.bird.png";

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
    private String mySelectedImage = "";
    //private static Map<String, Map<String,String>> sendToXML;


    public InputFieldCreator(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam) throws ParserConfigurationException, FileNotFoundException {
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
        this.setRight(configVBox);
    }

    /**
     * A getter method to return the selected image
     * @return
     */
    public String getMySelectedImage() {return mySelectedImage;
    }

    private void addInputFields() {

        configVBox = new VBox();

        for (int j = 0; j < properties.length; j++) {
            Label label = new Label(properties[j]);
            labelList.add(label);
            labelText.add(label.getText());
            configVBox.getChildren().add(label);
            configVBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }

        configVBox.setPrefWidth(150);
        configVBox.setPadding(new Insets(0, 20, 10, 20));
    }

    //
    private Button createSubmitButton(){
        Button addButton = new Button("Submit");
        addButton.setOnMouseClicked(event -> {
            allNodes
                    .stream()
                    .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

            SaveInputParameters myInputParameters = new SaveInputParameters(labelText, labelValue);
            getImageName(myInputParameters.getMap());
            String myLabel = xmlObject.addToSendToXMLMap(myInputParameters.getMap(), gameObjectName);
        });

        return addButton;
    }

    // a helper method to get a user-selected image file
    private void getImageName(Map<String, String> map) {
        for (Map.Entry s : map.entrySet()) {
            if (map.containsKey("image")) {
                mySelectedImage = s.getValue().toString();
            }
        }
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

    // a helper method to create a list of input field types for each tab
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

