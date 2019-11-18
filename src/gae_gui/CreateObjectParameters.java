package gae_gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.reflect.InvocationTargetException;
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


    public CreateObjectParameters(String[] propertiesParam, ResourceBundle paramFieldTypeP){
        properties = propertiesParam;
        towerPreferencePage = new Stage();
        root = new BorderPane();
        paramFieldType = paramFieldTypeP;
        addInputFields();
    }

    private void addInputFields() {

        vBox = new VBox();
        for (int j = 0; j < properties.length; j++) {
            vBox.getChildren().add(new Label(properties[j]));
            addAppropriateFieldType(properties[j]);
        }
        root.setCenter(vBox);
        towerEditScene = new Scene(root, window_WIDTH, window_HEIGHT);
        towerPreferencePage.setScene(towerEditScene);
        towerPreferencePage.show();
    }

    private void addAppropriateFieldType(String type){
        vBox.getChildren().add(createObjectFromString(paramFieldType.getString(type)));
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
