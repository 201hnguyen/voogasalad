package voogasalad.gameauthoringenvironment.gui.mapconfig;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.MapButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MapConfig {

    private static final int window_WIDTH = 1000;
    private static final int window_HEIGHT = 600;
    //private Group root;
    private BorderPane root;
    private Group subRoot;
    private Scene levelConfigScene;
    private SubScene playerField;

    private Stage levelConfigPage;
    private String towerResourcesPath = "gae_gui.gaeresource.";
    private int numberOfPaths = 0;
    private boolean pathCreation = false;
    private boolean settingSpawnPoint = false;
    private Label spawnPointLabel;
    private ArrayList<ArrayList<Pair<Double,Double>>> createdPathList;
    private ArrayList<Pair<Double,Double>> enemyPath;
    private ArrayList<Pair<Double,Double>> spawnPointList;

    private ArrayList<Label> createdPathLabel;
    private ArrayList<Label> createdSpawnPointsLabel;
    private Pair<Double,Double> spawnPoint;
    private int selectedPathIndex = 0;
    private int selectedWaveIndex = 0;
    private ArrayList<Integer> activeEnemyList ;
    private ArrayList<ArrayList<Integer>> waveComposition;
    private ArrayList<Label> waveCompositionLabel;
    private HBox mainhbox;
    private VBox controlVBox;
    private int yPosition = 10;
    private int xPosition = window_WIDTH - 160;
    private int spacing = 40;
    private int waveCount = 0;
    private ImageView spawnPointImageView;
    private Image spawnPointImage;
    private MapButton mapButtonInLevel;
    private Image pathPointImage;

    public MapConfig(MapButton mapButton){
        mapButtonInLevel = mapButton;
        activeEnemyList = new ArrayList<>(Arrays.asList(1,2,3));
        waveComposition = new ArrayList<>();
        waveCompositionLabel = new ArrayList<>();
        spawnPointList = new ArrayList<>();
        createdPathList = new ArrayList<>();
        createdPathLabel = new ArrayList<>();
        createdSpawnPointsLabel = new ArrayList<>();

        spawnPointImage = new Image(this.getClass().getClassLoader().getResourceAsStream("spawnPoint.jpg"));
        spawnPointImageView = new ImageView(spawnPointImage);
        pathPointImage = new Image(this.getClass().getClassLoader().getResourceAsStream("pathPoint.png"));
        spawnPointImageView.setFitHeight(20);
        spawnPointImageView.setFitWidth(20);
        levelConfigPage = new Stage();
        root = new BorderPane();
        //root = new Group();
        mainhbox = new HBox(10);
        controlVBox = new VBox(10);
        //addNewRouteButton();
        addSelectBackgroundButton();
        addPathCreationVBox();

        //addLabel();
        addInputField();
        addWaveEditingVBox();
        //addTowerTypeDropdown();

        //create subscene root
        subRoot = new Group();

        playerField = new SubScene(subRoot, 500, 500);
        playerField.setLayoutX(10);
        playerField.setLayoutY(10);
        playerField.setOnMouseClicked(e -> handleMouseClickedSubScene(e.getX(),e.getY()));
        //mainhbox.getChildren().add(playerField);
        //mainhbox.getChildren().add(buttonsvbox);
        //root.getChildren().add(mainhbox);

        root.setCenter(playerField);
        //root.getChildren().add(playerField);
        //root.getChildren().add(spawnPointImageView);
        controlVBox.getChildren().add(createSubmitAndCloseButton());
        root.setRight(controlVBox);

        levelConfigScene = new Scene(root, window_WIDTH, window_HEIGHT);
        levelConfigPage.setTitle("New Level Configuration");
        levelConfigPage.setScene(levelConfigScene);
        levelConfigPage.show();

    }

    private void handleMouseClickedSubScene(double xCoordinate, double yCoordinate){
        if (pathCreation) {
            enemyPath.add(new Pair(xCoordinate,yCoordinate));
            ImageView pathPointImageView = new ImageView(pathPointImage);
            pathPointImageView.setFitHeight(20);
            pathPointImageView.setFitWidth(20);
            pathPointImageView.setX(xCoordinate);
            pathPointImageView.setY(yCoordinate);
            subRoot.getChildren().add(pathPointImageView);
            System.out.println("x coordinate " + xCoordinate +" y coordinate " + yCoordinate);
            createdPathLabel.get(selectedPathIndex).setText(enemyPath.toString());

        }

        if (settingSpawnPoint) {
            if (!subRoot.getChildren().contains(spawnPointImageView)) {
                subRoot.getChildren().add(spawnPointImageView);
            }
            spawnPoint = new Pair(xCoordinate,yCoordinate);
            spawnPointLabel.setText(spawnPoint.toString());
            spawnPointImageView.setX(xCoordinate);
            spawnPointImageView.setY(yCoordinate);

            if (selectedPathIndex < spawnPointList.size()) {
                spawnPointList.set(selectedPathIndex, spawnPoint);
            } else {
                spawnPointList.add(spawnPoint);
            }

            createdSpawnPointsLabel.get(selectedPathIndex).setText(spawnPoint.toString());
            settingSpawnPoint = false;
        }

    }

    private void addPathCreationVBox(){
        VBox pathVBox = new VBox(10);
        Label vBoxTitle = new Label("Create And Edit Your Path");
        ScrollPane pathScrollPane = new ScrollPane();
        FlowPane pathFlowPane = new FlowPane();
        HBox flowPaneLabelHBox = new HBox(20);
        Label pathIndexLabel = new Label ("Path Index");
        Label pathCoordinateLabel = new Label("Path Points Coordinate");
        Label spawnPointCoordinateLabel = new Label("Spawn Point Coordinate");
        pathFlowPane.getChildren().add(spawnPointCoordinateLabel);
        flowPaneLabelHBox.getChildren().add(pathIndexLabel);
        flowPaneLabelHBox.getChildren().add(pathCoordinateLabel);
        flowPaneLabelHBox.getChildren().add(spawnPointCoordinateLabel);
        pathFlowPane.getChildren().add(flowPaneLabelHBox);
        pathScrollPane.setContent(pathFlowPane);

        HBox pathButtonHBox = new HBox(10);
        addCreateNewRouteButton(pathButtonHBox, pathFlowPane);
        addPathSelectionButton(pathButtonHBox);
        addSpawnPointButton(pathButtonHBox);

        pathVBox.getChildren().add(vBoxTitle);
        pathVBox.getChildren().add(pathButtonHBox);
        pathVBox.getChildren().add(pathScrollPane);
        controlVBox.getChildren().add(pathVBox);

    }

    private void addCreateNewRouteButton(HBox myHBox, FlowPane pathPane){
        Button creatNewRouteButton = new Button("Add A new Route");
        creatNewRouteButton.setOnAction(e -> addNewPathField(pathPane));
        myHBox.getChildren().add(creatNewRouteButton);
    }

    private void addNewPathField(FlowPane pathPane){
        HBox newPathHBox = new HBox(5);
        newPathHBox.setId(Integer.toString(numberOfPaths));//Id =number -1
        numberOfPaths ++;
        selectedPathIndex =numberOfPaths-1;



        Label waveNameLabel = new Label("Path " + numberOfPaths);
        Label pathListLabel = new Label("Click The Map To Add Point In Path");
        Label spawnPointLabel = new Label("To Be Set");
        createdPathLabel.add(pathListLabel);
        createdSpawnPointsLabel.add(spawnPointLabel);

        newPathHBox.getChildren().add(waveNameLabel);
        newPathHBox.getChildren().add(pathListLabel);
        newPathHBox.getChildren().add(spawnPointLabel);
        //root.getChildren().add(newWaveHBox);
        newPathHBox.setOnMouseClicked(e->selectedPathIndex=Integer.parseInt(newPathHBox.getId()));

        pathPane.getChildren().add(newPathHBox);


    }


    private void addSpawnPointButton(HBox myHBox){
        Button newButton = new Button("Add New Spawn Point");
        newButton.setLayoutX(window_WIDTH - 160 );
        newButton.setLayoutY(yPosition);
        yPosition+= spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->settingSpawnPoint = true);
        spawnPointLabel = new Label("To Be Set");
        spawnPointLabel.setLayoutX(window_WIDTH - 220);
        spawnPointLabel.setLayoutY(10);
        //root.getChildren().add(spawnPointLabel);
        //root.getChildren().add(newButton);
        myHBox.getChildren().add(spawnPointLabel);
        myHBox.getChildren().add(newButton);
    }

    private void addPathSelectionButton(HBox myHBox){
        Button newButton = new Button("Start Editing Current Path");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(yPosition);
        yPosition+=spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->turnOnOffPathCreation(newButton));
        //root.getChildren().add(newButton);
        myHBox.getChildren().add(newButton);

    }


    private void addInputField(){

    }

    private void addWaveEditingVBox(){
        VBox waveVBox = new VBox(10);
        Label vBoxTitle = new Label("Create And Edit Your Wave");


        ScrollPane waveScrollPane = new ScrollPane();
        FlowPane waveFlowPane = new FlowPane();
        waveScrollPane.setContent(waveFlowPane);
        HBox waveLabelHBox = new HBox(80);

        Label enemyListLabel = new Label("Enemy List");
        Label spawnTimeLabel = new Label("Spawn Time");
        Label durationLabel = new Label("Duration");
        Label pathLabel = new Label("Selected Path");
        waveLabelHBox.getChildren().add(enemyListLabel);
        waveLabelHBox.getChildren().add(spawnTimeLabel);
        waveLabelHBox.getChildren().add(durationLabel);
        waveLabelHBox.getChildren().add(pathLabel);
        waveFlowPane.getChildren().add(waveLabelHBox);

        HBox enemyTypeHBox = new HBox(5);
        for (int enemyIndex : activeEnemyList) {
            Button newEnemyType = new Button("Enemy Type " + enemyIndex );
            newEnemyType.setOnAction(event -> addEnemyToWave(enemyIndex));
            enemyTypeHBox.getChildren().add(newEnemyType);
        }

        waveVBox.getChildren().add(vBoxTitle);
        addWaveButton(waveVBox, waveFlowPane);
        waveVBox.getChildren().add(enemyTypeHBox);
        waveVBox.getChildren().add(waveScrollPane);

        controlVBox.getChildren().add(waveVBox);





    }
    private void addWaveButton(VBox myVBox, FlowPane myWaveFlowPane){


        Button addWaveButton = new Button("Add A New Wave");
        addWaveButton.setLayoutX(xPosition);
        addWaveButton.setLayoutY(yPosition);
        yPosition+=spacing;
        addWaveButton.setOnAction(e->addNewWaveField(myWaveFlowPane));


        //root.getChildren().add(addWaveButton);
        //root.getChildren().add(enemyTypeHBox);
        //root.getChildren().add(waveLabelHBox);
        myVBox.getChildren().add(addWaveButton);

    }

    private void addEnemyToWave(int enemyIndex) {
        waveComposition.get(selectedWaveIndex).add(enemyIndex);
        waveCompositionLabel.get(selectedWaveIndex).setText(waveComposition.get(selectedWaveIndex).toString());

    }
    private void addNewWaveField(FlowPane myFlowPane){
        ArrayList<Integer> newWaveEnemyList = new ArrayList<>();
        HBox newWaveHBox = new HBox(5);
        newWaveHBox.setId(Integer.toString(waveCount));//ID = index in array
        waveComposition.add(newWaveEnemyList);
        selectedPathIndex = waveCount;
        waveCount ++;

        newWaveHBox.setLayoutY(yPosition);
        yPosition+=spacing;
        newWaveHBox.setLayoutX(xPosition-320);
        Label waveNameLabel = new Label("Wave " + waveCount);
        Label waveEnemyListLabel = new Label("Click Active Enemy Type to Add");
        waveCompositionLabel.add(waveEnemyListLabel);
        TextField startingTimeField = new TextField();
        TextField durationField = new TextField();
        ComboBox availablePaths = new ComboBox();

        ObservableList<String> choices = FXCollections.observableArrayList("a", "b", "c");
        availablePaths.setItems(choices);
        newWaveHBox.getChildren().add(waveNameLabel);
        newWaveHBox.getChildren().add(waveEnemyListLabel);
        newWaveHBox.getChildren().add(startingTimeField);
        newWaveHBox.getChildren().add(durationField);
        newWaveHBox.getChildren().add(availablePaths);
        //root.getChildren().add(newWaveHBox);
        newWaveHBox.setOnMouseClicked(e->selectedWaveIndex = Integer.parseInt(newWaveHBox.getId()));
        myFlowPane.getChildren().add(newWaveHBox);


    }


    private TextField createInputField(String fieldName){
        TextField newInputField = new TextField();
        newInputField.setId(fieldName);
        //newInputField.setOnAction(e -> );
        return newInputField;

    }



    private void turnOnOffPathCreation(Button myButton) {
        pathCreation = !pathCreation;
        if (pathCreation) {
            enemyPath = new ArrayList<>();
            myButton.setText("Stop Editing Path ");
        } else {
            if (selectedPathIndex < createdPathList.size()) {
                createdPathList.set(selectedPathIndex, enemyPath);
            } else {
                createdPathList.add(enemyPath);
            }
            myButton.setText("Edit Selected Path");
        }

    }


    private void addSelectBackgroundButton(){
        final FileChooser imageChooser = new FileChooser();
        Button newButton = new Button("Select Background Image");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(yPosition);
        yPosition+=spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(levelConfigPage);
            if (file != null) {
                Image image1 = new Image(file.toURI().toString());
                ImageView ip = new ImageView(image1);
                ip.setFitWidth(500);
                ip.setFitHeight(500);
                subRoot.getChildren().add(ip);
            }
        });

        //root.getChildren().add(newButton);
        controlVBox.getChildren().add(newButton);
    }

    private Button createSubmitAndCloseButton(){
        Button submitClose = new Button("Submit Map");
        submitClose.setOnMouseClicked(event -> {
            mapButtonInLevel.mapSubmitted();
            levelConfigPage.close();
        });
        return submitClose;
    }


}
