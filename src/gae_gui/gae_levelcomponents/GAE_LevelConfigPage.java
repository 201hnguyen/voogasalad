package gae_gui.gae_levelcomponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GAE_LevelConfigPage {

    private static final int window_WIDTH = 1000;
    private static final int window_HEIGHT = 600;
    private Group root;
    private Group subRoot;
    private Scene levelConfigScene;
    private SubScene playerField;

    private Stage levelConfigPage;
    private String towerResourcesPath = "gae_gui.gaeresource.";
    private int numberOfPaths = 0;
    private boolean pathCreation = false;
    private boolean settingSpawnPoint = false;
    private Label spawnPointLabel;
    private ArrayList<Pair<Double,Double>> enemyPath;
    private Pair<Double,Double> spawnPoint;


    public GAE_LevelConfigPage(){



        levelConfigPage = new Stage();
        //root = new GridPane();
        root = new Group();
        //addNewRouteButton();
        createNewRoute();
        addSelectBackgroundButton();
        //addLabel();
        addInputField();
        //addTowerTypeDropdown();

        //create subscene root
        subRoot = new Group();
        playerField = new SubScene(subRoot, 500, 500);
        playerField.setLayoutX(50);
        playerField.setLayoutY(50);
        playerField.setOnMouseClicked(e -> handleMouseClickedSubScene(e.getX(),e.getY()));
        root.getChildren().add(playerField);


        levelConfigScene = new Scene(root, window_WIDTH, window_HEIGHT);
        levelConfigPage.setTitle("New Level Configuration");
        levelConfigPage.setScene(levelConfigScene);
        levelConfigPage.show();

    }

    private void handleMouseClickedSubScene(double xCoordinate, double yCoordinate){
        if (pathCreation) {
            enemyPath.add(new Pair(xCoordinate,yCoordinate));
            System.out.println("x coordinate " + xCoordinate +" y coordinate " + yCoordinate);

        }

        if (settingSpawnPoint) {
            spawnPoint = new Pair(xCoordinate,yCoordinate);
            spawnPointLabel.setText(spawnPoint.toString());
            settingSpawnPoint = false;
        }

    }

    private void addInputField(){

    }



    private TextField createInputField(String fieldName){
        TextField newInputField = new TextField();
        newInputField.setId(fieldName);
        //newInputField.setOnAction(e -> );
        return newInputField;

    }

    private void addNewRouteButton(){
        Button applyButton = new Button("Add New Route");
        applyButton.setId("NewRoute");
        applyButton.setOnAction(e ->createNewRoute());
        root.getChildren().add(applyButton);
    }


    private void createNewRoute(){
        numberOfPaths ++;
        addSpawnPointButton();
        addPathSelectionButton();
    }

    private void addSpawnPointButton(){
        Button newButton = new Button("Add New Spawn Point");
        newButton.setLayoutX(window_WIDTH - 160 );
        newButton.setLayoutY(10);
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->settingSpawnPoint = true);
        spawnPointLabel = new Label("To Be Set");
        spawnPointLabel.setLayoutX(window_WIDTH - 220);
        spawnPointLabel.setLayoutY(10);
        root.getChildren().add(spawnPointLabel);
        root.getChildren().add(newButton);
    }

    private void turnOnOffPathCreation(Button myButton) {
        pathCreation = !pathCreation;
        if (pathCreation) {
            enemyPath = new ArrayList<>();
            myButton.setText("Stop Making Path ");
        } else {
            myButton.setText("Add New Path");
        }

    }

    private void addPathSelectionButton(){
        Button newButton = new Button("Start Making New Path");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(50);
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->turnOnOffPathCreation(newButton));

        root.getChildren().add(newButton);

    }

    private void addSelectBackgroundButton(){
        final FileChooser imageChooser = new FileChooser();
        Button newButton = new Button("Select Background Image");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(90);
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->createNewRoute());
        newButton.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(levelConfigPage);
            if (file != null) {
                Image image1 = new Image(file.toURI().toString());
                ImageView ip = new ImageView(image1);
                subRoot.getChildren().add(ip);
            }
        });

        root.getChildren().add(newButton);
    }


}
