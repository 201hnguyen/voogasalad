package gae_gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CreateObjectParameters {

    private static final int window_WIDTH = 300;
    private static final int window_HEIGHT = 300;
    private BorderPane root;
    private Scene towerEditScene;
    private Stage towerPreferencePage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox vBox;
    //private AddToXML xmlObject;
    private String gameObjectName;
    private List<Label> labelList = new ArrayList<Label>();

    private List<String> labelText = new ArrayList<String>();
    private List<String> labelValue = new ArrayList<String>();



    public CreateObjectParameters(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam){
        properties = propertiesParam;
        towerPreferencePage = new Stage();
        root = new BorderPane();
        root.setBottom(createSubmitButton()); //put this in add input fields?
        paramFieldType = paramFieldTypeParam;
        gameObjectName = gameObjectNameParam;
        addInputFields();
    }


    //not in use
    //called in SaveGuiParameters
    public String getTextFromInputFields() {
        String value = "";
        return value;
    }


    private void addInputFields() {

        vBox = new VBox();
        for (int j = 0; j < properties.length; j++) {

            Label label = new Label(properties[j]); //for SaveGuiParameters


            labelList.add(label);
            labelText.add(label.getText());
            labelValue.add(label.getAccessibleText());

            vBox.getChildren().add(label);
            vBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }

        //System.out.println(labelText); //testing
        //System.out.println(labelValue); //testing

        root.setCenter(vBox);
        towerEditScene = new Scene(root, window_WIDTH, window_HEIGHT);
        towerPreferencePage.setScene(towerEditScene);
        towerPreferencePage.show();
    }

    //does not update, but creates map of labels and values
    private Button createSubmitButton(){
        Button addButton = new Button("Submit");
        addButton.setOnMouseClicked(event -> {
            //labelValue.add(label.getAccessibleText());
            SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
//            for (Label myLabel: labelList){
//                System.out.println(myLabel.getAccessibleText());
//            }
//            //System.out.println(label);
        });
        return addButton;
    }

    private Node createObjectFromString(String type){
        try{
            Class cls = Class.forName(type);
            return (Node) cls.getConstructor().newInstance();
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
}
