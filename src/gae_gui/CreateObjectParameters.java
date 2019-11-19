package gae_gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateObjectParameters {

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private BorderPane root;
    private Scene towerEditScene;
    private Stage towerPreferencePage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox vBox;
    private AddToXML xmlObject;
    private String gameObjectName;
    private List<Node> allNodes = new ArrayList<>();
    private List<String> fieldTypes= new ArrayList<>();
    FieldTextReturnFactory fieldFactory = new FieldTextReturnFactory();


    public CreateObjectParameters(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam){
        properties = propertiesParam;
        towerPreferencePage = new Stage();
        root = new BorderPane();
        root.setBottom(createSubmitButton());
        paramFieldType = paramFieldTypeParam;
        gameObjectName = gameObjectNameParam;
        storeAllFieldTypes();
        addInputFields();
    }

    private void addInputFields() {

        vBox = new VBox();
        for (int j = 0; j < properties.length; j++) {
            vBox.getChildren().add(new Label(properties[j]));
            vBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }
        root.setCenter(vBox);
        towerEditScene = new Scene(root, window_WIDTH, window_HEIGHT);
        towerPreferencePage.setScene(towerEditScene);
        towerPreferencePage.show();
    }

    private Button createSubmitButton(){
        Button addButton = new Button("Submit");
        addButton.setOnMouseClicked(event -> {
//            try {
//                xmlObject = new AddToXML(gameObjectName, properties);
//            } catch (ParserConfigurationException e) {
//                throw new Error(e);
//            }
        allNodes
                .stream()
                .forEach(node -> System.out.println(fieldFactory.getAppropriateText(node)));

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


}
