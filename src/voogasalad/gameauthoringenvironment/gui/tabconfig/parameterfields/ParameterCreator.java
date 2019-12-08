package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveGUIParameters;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.utils.FileChooserButton;
import voogasalad.gameauthoringenvironment.gui.utils.PreviewImageButton;
import voogasalad.gameauthoringenvironment.gui.utils.SubmitButton;
import voogasalad.gameauthoringenvironment.gui.utils.TabVBoxCreator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ParameterCreator extends BorderPane{

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private static final String SUBMITBUTTONCLASS = new SubmitButton().getClass().toString().split("class ")[1];
    private static final String FILECHOOSERBUTTONCLASS = "class voogasalad.gameauthoringenvironment.gui.utils.FileChooserButton";
    private static final String PREVIEWIMAGEBUTTONCLASS = "class voogasalad.gameauthoringenvironment.gui.utils.PreviewImageButton";
    private static final String PROPERTIES_PATH = "resources.gae.tabcreation";

    private BorderPane root;
    private ObjectPreviewAndActive objectSpecificRoot;
    private Stage newStage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox configVBox;
    private VBox previewVBox;
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
    private Map<String, Map<String, Map<String, String>>> allActiveObjectMap;
    private Properties imageProp;
    private String imageString;
    private ImageView imageView;
    double imageViewWidth = 0;
    double imageViewHeight = 0;
    PreviewImageButton previewImageButton;
    FileChooserButton fileChooserButton;

    //private static Map<String, Map<String,String>> sendToXML;


    public ParameterCreator(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam,
                            LevelConfigPane levelConfigPaneParam, Map<String, Map<String, Map<String, String>>> allActiveObjectMapParam) throws ParserConfigurationException {
        fileChooserButton = new FileChooserButton();
        allActiveObjectMap = allActiveObjectMapParam;
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
        //addImagePreview();
        createImagePreview();
        this.setRight(configVBox);
        this.setLeft(previewVBox);
    }

    public void createSubmitButton(){
        allNodes
                .stream()
                .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

        SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
        allActiveObjectMap.put(gameObjectName, activeObjects);
        String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
        addToAppropriateField(gameObjectName, createObjectIcon(myGuiParameters.getMap(), myLabel));
    }

    private void addInputFields() {
        configVBox = new TabVBoxCreator("Configure Parameters");

        for (int j = 0; j < properties.length; j++) {
            Label label = new Label(properties[j]); //for SaveGuiParameters
            labelList.add(label);
            labelText.add(label.getText());
            configVBox.getChildren().add(label);
            configVBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }
    }

    //not in use
    private void addImagePreview() {
        previewVBox = new TabVBoxCreator("Image Preview");


        //load properties file
        try (InputStream reader = new FileInputStream(PROPERTIES_PATH)) {

            imageProp = new Properties();

            clearPropertiesFile(reader);

            // load a properties file
            imageProp.load(reader);

            imageString = imageProp.getProperty("image.url");
            System.out.println(imageString);

            imageView = new ImageView(imageString);
            previewVBox.getChildren().add(imageView);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    //not in use
    private void clearPropertiesFile(InputStream reader) throws IOException {
        //InputStream reader = new FileInputStream(PROPERTIES_PATH);
        OutputStream writer = new FileOutputStream(PROPERTIES_PATH);
        imageProp.load(reader);
        imageProp.remove("image.url");
        imageProp.store(writer, null);
    }



    private void createImagePreview() {
        previewVBox = new TabVBoxCreator("Image Preview");

        for (int i = 0; i < allNodes.size(); i++) {
            Node currentNode = allNodes.get(i);
            if (currentNode instanceof FileChooserButton) {
                fileChooserButton = (FileChooserButton) currentNode;
            }
            if (allNodes.get(i) instanceof PreviewImageButton) {
                PreviewImageButton button = (PreviewImageButton) currentNode;
                button.setOnAction(event -> {
                    imageString = fileChooserButton.getImageString();
                    imageView = new ImageView(imageString);
                    previewVBox.getChildren().add(new ImageView(new Image(imageString)));
                });
            }
        }
    }



    private Node createObjectFromString(String type){
        try{
            if (type.equals(SUBMITBUTTONCLASS)) {
                SubmitButton myField = new SubmitButton(this);
                return myField;
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

    public Map<String, Map<String, String>> getActiveObjects() {
        return activeObjects;
    }



}

